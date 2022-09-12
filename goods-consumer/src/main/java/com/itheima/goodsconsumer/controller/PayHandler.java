package com.itheima.goodsconsumer.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.itheima.b2b.commonmodule.model.Address;
import com.itheima.b2b.commonmodule.model.Cart;
import com.itheima.goodsconsumer.config.OrderVO;
import com.itheima.goodsconsumer.config.PayProperties;
import com.itheima.goodsconsumer.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/goods")
public class PayHandler {

    @Autowired
    private PayProperties payProperties;

    @Autowired
    private GoodsService goodsService;

    // 通过ResponseBody注解，让当前方法的返回值成为响应体，在浏览器上显示支付宝的支付界面
    @ResponseBody
    @RequestMapping("generate/order")
    /**
     *
     */
    public String generateOrder(OrderVO orderVO, HttpSession session, String moneyNow) throws UnsupportedEncodingException, AlipayApiException {

        // 生成支付宝订单号
        // 使用uuid生成用户id部分
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

        // 根据日期生成字符串
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // 组装
        String orderNum = time + uuid;

        // 存入orderVO
        orderVO.setOrderNum(orderNum);

        // 计算订单金额
        Double orderAmount = Double.valueOf(moneyNow);

        // 存入orderVO
        orderVO.setOrderAmount(orderAmount);

        // 把orderVO存入session域
        session.setAttribute("orderVO", orderVO);

        return sendRequestToAliPay(orderNum,orderAmount,"商品订单","商品描述");
    }

    /**
     *
     * @param orderNum 订单号
     * @param orderAmount 总金额
     * @param subject 订单名称，这里用项目名称
     * @param body 商品描述，这里用回报的描述
     * @return 返回页面
     * @throws AlipayApiException
     * @throws UnsupportedEncodingException
     */
    private String sendRequestToAliPay(String orderNum, Double orderAmount, String subject, String body) throws AlipayApiException, UnsupportedEncodingException {

        System.out.println("-------sendRequestToAliPay-------");

        /**
         * 订单信息操作
         */
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        String phonenum = (String) session.getAttribute("phonenum");

        String goodsnameNew = (String) session.getAttribute("goodsname");
        String gpriceNew = (String) session.getAttribute("gprice");
        String numberNew = (String) session.getAttribute("number");
        String idNew = (String) session.getAttribute("id");

        String[] goodsnames = goodsnameNew.split(",");
        String[] gprices = gpriceNew.split(",");
        String[] numbers = numberNew.split(",");
        String[] ids = idNew.split(",");

        // 收货地址
        List<Address> addressList = goodsService.getAlladdress(phonenum);

        /**
         * 遍历，进行数据分离
         */

        // 根据id查询对应的goodid
        List<Cart> cartList = goodsService.getAllcart(phonenum);

        for(int i=0; i<ids.length; i++){

            if(!ids[i].equals("null")){

                for(int j=0; j< cartList.size(); j++){

                    if(String.valueOf(cartList.get(j).getId()).equals(ids[i])){

                        Double prices = Double.parseDouble(gprices[i]);
                        String orderid = orderNum;
                        String goodsname = goodsnames[i];
                        int number = Integer.parseInt(numbers[i]);
                        double price = prices;
                        String receivername = addressList.get(0).getReceivername();
                        String receiverphone = addressList.get(0).getReceiverphone();
                        String address = addressList.get(0).getAddress();
                        //生成订单插入订单
                        goodsService.insertOrder(orderid, goodsname, number, price, phonenum, receivername, receiverphone, address);
                        //清空购物车
                        goodsService.deleteCart(goodsService.deleteCart(Integer.parseInt(ids[i])));

                    }
                }
            }
        }

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                payProperties.getGatewayUrl(),
                payProperties.getAppId(),
                payProperties.getMerchantPrivateKey(),
                "json",
                payProperties.getCharset(),
                payProperties.getAliPayPublicKey(),
                payProperties.getSignType());

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(payProperties.getReturnUrl());
        alipayRequest.setNotifyUrl(payProperties.getNotifyUrl());

        //设置订单内容：商品名称 + 商品描述；
        subject = "";
        for(int i=0; i<goodsnames.length; i++) {
            if (!goodsnames[i].equals("null")) {
                subject += goodsnames[i];
            }
        }
        body = "水果电子商城购买商品";
        //body = (String) session.getAttribute("gdetails");


        alipayRequest.setBizContent("{\"out_trade_no\":\""+ orderNum +"\","
                + "\"total_amount\":\""+ orderAmount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        // 返回
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

    @ResponseBody
    @RequestMapping("/return")
    public String returnUrlMethod(HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException, AlipayApiException {

        System.out.println("-------returnUrlMethod-------");

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(
                params,
                payProperties.getAliPayPublicKey(),
                payProperties.getCharset(),
                payProperties.getSignType()); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {

            System.out.println("-------您的程序-------");

            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            // 得到session域中的orderVO
            OrderVO orderVO = (OrderVO)session.getAttribute("orderVO");

            // 设置支付宝交易号
            orderVO.setPayOrderNum(trade_no);

            log.error("save order result: " + orderVO);

            return "trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount+"<br/><a href='http://localhost:8896/goods/getAll'>点我返回主页</a>";

        }else {
            return "验签失败";
        }
    }



    @RequestMapping("/notify")
    public void notifyUrlMethod(HttpServletRequest request, HttpSession session) throws AlipayApiException, UnsupportedEncodingException {

        System.out.println("-------notifyUrlMethod-------");

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(
                params,
                payProperties.getAliPayPublicKey(),
                payProperties.getCharset(),
                payProperties.getSignType()); //调用SDK验证签名
        //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if(signVerified) {//验证成功

            System.out.println("-------验证成功-------");

            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }

            log.error("success");
            log.error("out_trade_no" + out_trade_no);
            log.error("trade_no" + trade_no);
            log.error("trade_status" + trade_status);

        }else {//验证失败
            log.error("fail");
        }
    }
}
