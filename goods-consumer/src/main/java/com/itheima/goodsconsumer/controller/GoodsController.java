package com.itheima.goodsconsumer.controller;

import com.itheima.b2b.commonmodule.model.Address;
import com.itheima.b2b.commonmodule.model.Cart;
import com.itheima.b2b.commonmodule.model.Collect;
import com.itheima.b2b.commonmodule.model.Goods;
import com.itheima.goodsconsumer.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @GetMapping(value = "quit")
    public String quit(HttpSession session) {
        session.removeAttribute("phonenum");
        return "redirect:http://localhost:8893/admin/tologin";
    }

    /**
     * 首页查询全部商品显示
     *
     * @param request
     * @param phonenum
     * @param session
     * @return
     */
    @GetMapping(value = "/getAll")
    public String getAll(HttpServletRequest request, String phonenum, HttpSession session, Model model) {

        if (session.getAttribute("phonenum") != null) {
            session.setAttribute("phonenum", String.valueOf(session.getAttribute("phonenum")));
            phonenum = (String) session.getAttribute("phonenum");
        } else {
            session.setAttribute("phonenum", phonenum);
        }

        //System.out.println("getAll-phonenum:" + phonenum);

        List<Goods> oldgoods = goodsService.getAllgoods();
        List<Goods> discountgoods = goodsService.getDiscount();

        for (int i = 0; i < oldgoods.size(); i++) {
            for (int j = 0; j < discountgoods.size(); j++) {
                if (oldgoods.get(i).getGid().equals(discountgoods.get(j).getDiscount().getGoodsid())) {
                    oldgoods.remove(i);
                }
            }
        }

        //map对实现前后数据交互 1.普通商品：goods1   2.打折商品：goods2
        request.setAttribute("goods2", discountgoods); // 打折商品
        request.setAttribute("goods1", oldgoods); // 普通商品

        // 查询购物车全部商品
        List<Cart> cartList = goodsService.getAllcart(phonenum);
        List<Goods> goodsListCart = new ArrayList<>();
        if (oldgoods != null) {
            for (int i = 0; i < cartList.size(); i++) {
                for (int j = 0; j < oldgoods.size(); j++) {
                    if (cartList.get(i).getGoodsname().equals(oldgoods.get(j).getGname())) {
                        goodsListCart.add(oldgoods.get(j));
                    }
                }
            }
        }
        model.addAttribute("goodsListCart", goodsListCart);
        model.addAttribute("cartList", cartList);
        model.addAttribute("size", goodsListCart.size());

        // 默认进入首页时，显示第一页，默认页面16条数据
        /**
         * 存在2种情况
         * 1、打折商品大于16条
         * 2、打折商品小于16条
         * 打折商品：discountgoods
         * 普通商品：oldgoods
         */
        if (discountgoods.size() > 12) {
            // 使用 subList 进行 list 数组截取
            request.setAttribute("goods2", discountgoods.subList(0, 12)); // 打折商品
            request.setAttribute("goods1", null);
            // 普通商品
        } else {
            request.setAttribute("goods2", discountgoods); // 打折商品
            request.setAttribute("goods1", oldgoods.subList(0, 12 - discountgoods.size())); // 普通商品
        }
        // 初始化页数
        request.setAttribute("pageNow", 1);
        // 存放全部数据量
        request.setAttribute("sizeAll", (discountgoods.size() + oldgoods.size()) / 12);

        return "shop";
    }

    /**
     * 商品详情-即根据ID查询单个商品
     *
     * @param request
     * @param gid
     * @return
     */
    @GetMapping(value = "/detail")
    public String detail(HttpServletRequest request, String gid) {
        Goods goods = goodsService.getOnegid(gid);
        List<Goods> discountgoods = null;
        if (goods != null) {
            discountgoods = goodsService.getDiscount();
            for (int j = 0; j < discountgoods.size(); j++) {
                if ((discountgoods.get(j).getDiscount().getGoodsid().equals(gid))) {
                    goods.setGprice(Double.valueOf(discountgoods.get(j).getDiscount().getDiscountnum()) * goods.getGprice());
                }
            }
        }

        request.setAttribute("detail", goods);
        return "variable-product";
    }

    /**
     * 商品首页搜索-根据名称模糊查找商品(整合系统分页)
     *
     * @param request
     * @param gname
     * @param pageNow
     * @return
     */
    @GetMapping(value = "/getOne")
    public String getOne(HttpServletRequest request,
                         String gname,
                         Integer pageNow,
                         HttpSession session,
                         Model model) {
        // 获取当前用户信息
        String phonenum = (String) session.getAttribute("phonenum");

        // 查询购物车全部商品
        List<Goods> oldgoods = goodsService.getAllgoods();
        List<Cart> cartList = goodsService.getAllcart(phonenum);
        List<Goods> goodsListCart = new ArrayList<>();
        if (oldgoods != null) {
            for (int i = 0; i < cartList.size(); i++) {
                for (int j = 0; j < oldgoods.size(); j++) {
                    if (cartList.get(i).getGoodsname().equals(oldgoods.get(j).getGname())) {
                        goodsListCart.add(oldgoods.get(j));
                    }
                }
            }
        }
        model.addAttribute("goodsListCart", goodsListCart);
        model.addAttribute("cartList", cartList);
        model.addAttribute("size", goodsListCart.size());

        // 其他信息
        System.out.println("gname = " + gname);

        List<Goods> discountgoods = goodsService.getDiscount();

        // 筛选
        List<Goods> goodsOne = new ArrayList<>();
        if (gname != null && gname != "") {
            goodsOne = goodsService.getOnegoods(gname);
        } else {
            goodsOne = goodsService.getAllgoods();
        }

        System.out.println("oldgoods = " + oldgoods);
        System.out.println("discountgoods = " + discountgoods);
        System.out.println("goodsOne = " + goodsOne);

        List<Goods> goodsOneN = new ArrayList<>();
        for (Goods goods1 : discountgoods) {
            for (Goods goods2 : goodsOne) {
                if (goods1.getGid().equals(goods2.getGid())) {
                    goodsOneN.add(goods1);
                }
            }
        }

        for (int i = 0; i < goodsOne.size(); i++) {
            for (int j = 0; j < goodsOneN.size(); j++) {
                if (goodsOne.get(i).getGid().equals(goodsOneN.get(j).getDiscount().getGoodsid())) {
                    goodsOne.remove(i);
                }
            }
        }

        /**
         * 存在2种情况
         * 1、打折商品大于16条
         * 2、打折商品小于16条
         * 打折商品：goodsOneN
         * 普通商品：goodsOne
         */
        // 存放当前页数
        request.setAttribute("pageNow", pageNow);
        // 存放全部数据量
        request.setAttribute("sizeAll", (discountgoods.size() + oldgoods.size()) / 12);

        if (goodsOneN.size() > 12) {
            // 判断后面的情况，是否存在 goodsOneN.size() - 12*pageNow > 12
            if (goodsOneN.size() - 12 * pageNow > 12) { // 仍然是打折商品填满页面
                // 使用 subList 进行 list 数组截取
                request.setAttribute("goods2", goodsOneN.subList(12 * (pageNow - 1), 12 * pageNow > goodsOneN.size() ? goodsOneN.size() : 12 * pageNow)); // 打折商品
                request.setAttribute("goods1", null); // 普通商品
            } else {
                /**
                 * 说明当前页面存在打折商品和普通商品
                 * 计算打折商品剩余数量：goodsOneN.size() - 12*(pageNow-1)
                 * 需要的普通商品数量：12 - goodsOneN.size() - 12*(pageNow-1)
                 */
                request.setAttribute("goods2", goodsOneN.subList(12 * (pageNow - 1), goodsOneN.size() - 12 * (pageNow - 1))); // 打折商品
                request.setAttribute("goods1", goodsOne.subList(goodsOneN.size() - 12 * (pageNow - 1), 12 - goodsOneN.size() - 12 * (pageNow - 1) > goodsOne.size() ? goodsOne.size() : 12 - goodsOneN.size() - 12 * (pageNow - 1))); // 普通商品
            }
        } else {
            if (pageNow > 1) {
                int pageOneNow = 12 - goodsOneN.size();
                request.setAttribute("goods2", null); // 打折商品
                request.setAttribute("goods1", goodsOne.subList(pageOneNow + 12 * (pageNow - 2), (pageOneNow + 12 * (pageNow - 1) + 12) > goodsOne.size() ? goodsOne.size() : pageOneNow + 12 * (pageNow - 1) + 12)); // 普通商品
            } else {
                request.setAttribute("goods2", goodsOneN); // 打折商品
                request.setAttribute("goods1", goodsOne.subList(0, 12 - goodsOneN.size() > goodsOne.size() ? goodsOne.size() : 12 - goodsOneN.size())); // 普通商品
            }
        }

        System.out.println("=================");
        System.out.println(goodsOneN.size());
        System.out.println(goodsOne.size());

        return "shop";
    }

    /**
     * 删除购物车，根据id删除购物车商品（即购买之后删除购物车商品）
     *
     * @param request
     * @param did
     * @return
     */
    @GetMapping(value = "deleteCart")
    public String deleteCart(HttpServletRequest request, String did) {
        goodsService.deleteCart(Integer.parseInt(did));
        return "redirect:/goods/cartAll";
    }

    /**
     * 订单确认界面
     *
     * @param request
     * @param
     * @param number
     * @param session
     * @param
     * @return
     */
    @PostMapping(value = "/goodsOrder")
    public String goodsOrder(HttpServletRequest request, HttpSession session,
                             String goodsname,
                             String gprice,
                             String number,
                             String id) {

        String phonenum = (String) session.getAttribute("phonenum");

        /***
         * goodsname:脉动,,胡萝卜
         * gprice:2.8,,2.0
         * number:3,1,3
         * id:22,,26
         */

//        System.out.println("goodsname:" + goodsname);
//        System.out.println("gprice:" + gprice);
//        System.out.println("number:" + number);
//        System.out.println("id:" + id);

        session.setAttribute("goodsname", goodsname);
        session.setAttribute("gprice", gprice);
        session.setAttribute("number", number);
        session.setAttribute("id", id);

        String[] gprices = gprice.split(",");
        String[] numbers = number.split(",");
        String[] ids = id.split(",");

        /**
         * 遍历，进行数据分离
         */

        // 将对应的信息存放到list中
        List<Goods> goodsList = new ArrayList<>();

        // 根据id查询对应的goodid
        List<Cart> cartList = goodsService.getAllcart(phonenum);

        // 计算总金额
        Double moneyNow = 0.0;
        for(int i=0; i<ids.length; i++){

            if(!ids[i].equals("null")){

                for(int j=0; j< cartList.size(); j++){

                    if(String.valueOf(cartList.get(j).getId()).equals(ids[i])){

                        // 查询对应商品的信息
                        Goods goods = goodsService.getOnegid(cartList.get(j).getGoodid());
                        // 填入对应信息，将商品价格改为实际价格，商品标签替换为商品数量
                        goods.setGprice(Double.parseDouble(gprices[i]));
                        goods.setTypes(Integer.valueOf(numbers[i]));

                        goodsList.add(goods);

                        moneyNow += Double.parseDouble(numbers[i]) * Double.parseDouble(gprices[i]);

                    }
                }
            }
        }

        request.setAttribute("goodsList", goodsList);
        request.setAttribute("moneyNow", moneyNow);

        // 收货地址
        List<Address> addressList = goodsService.getAlladdress(phonenum);

        request.setAttribute("addressList", addressList);


        return "order";
    }

    /**
     * 根据用户查出用户下所有订单
     *
     * @param request
     * @param session
     * @return
     */
    @GetMapping(value = "/getAllorder")
    public String getAllorder(HttpServletRequest request, HttpSession session) {
        String phonenum = (String) session.getAttribute("phonenum");

        if (phonenum.equals(null)) {
            return "redirect:http://localhost:8893/admin/tologin";
        } else {
            request.setAttribute("order", goodsService.getAllorder(phonenum));
            return "wishlist";
        }

    }

    /**
     * 根据物品名称查出用户下订单
     *
     * @param request
     * @param session
     * @return
     */
    @GetMapping(value = "/getAllorderBygname")
    public String getAllorderBygname(HttpServletRequest request, HttpSession session) {
        String phonenum = (String) session.getAttribute("phonenum");
        String gname = request.getParameter("gname");
        if (phonenum.equals(null)) {
            return "redirect:http://localhost:8893/admin/tologin";
        } else {
            request.setAttribute("order", goodsService.getAllorderBygname(phonenum, gname));
            return "wishlist";
        }

    }

    /**
     * 查询购物车全部商品
     *
     * @param request
     * @param session
     * @return
     */
    @GetMapping(value = "cartAll")
    public String cartAll(HttpServletRequest request, HttpSession session, Model model) {
        String phonenum = (String) session.getAttribute("phonenum");
        /* request.setAttribute("carts",goodsService.getAllcart(Integer.parseInt(uid)));*/
        List<Cart> cartList = goodsService.getAllcart(phonenum);
        List<Address> addressList = goodsService.getAlladdress(phonenum);

        model.addAttribute("carts", cartList);
        model.addAttribute("addressList", addressList);

        //System.out.println("carts:" + goodsService.getAllcart(phonenum));
        System.out.println("addressList:" + goodsService.getAlladdress(phonenum));
        return "cart";
    }

    /**
     * 加入购物车-将商品加入购物车并查询出全部商品如果有商品修改商品数量
     *
     * @param request
     * @param
     * @param price
     * @param
     * @param number
     * @param session
     * @return
     */
    @GetMapping(value = "cart")
    public String cart(HttpServletRequest request, String gid, String gname, String gremain, String price,String number, HttpSession session) {
        String phonenum = (String) session.getAttribute("phonenum");
//        System.out.println("gid:" + gid);
//        System.out.println("gname:" + gname);
//        System.out.println("gremain:" + gremain);
//        System.out.println("price:" + price);
//        System.out.println("number:" + number);
        goodsService.intcart(gid, gname, gremain, Integer.parseInt(number), Double.parseDouble(price), phonenum);
        return "redirect:/goods/cartAll";
    }


    @RequestMapping("hindex")
    public String hindex() {
        return "hindex";
    }

    @RequestMapping("hindexallgoods")
    public String hindexallgoods(HttpServletRequest request) {


        List<Goods> allgoods = goodsService.getAllgoods();
        List<Goods> discountgoods = goodsService.getDiscount();


        for (int i = 0; i < allgoods.size(); i++) {
            System.out.println("gid:" + allgoods.get(i).getGid());
            for (int j = 0; j < discountgoods.size(); j++) {
                System.out.println("disgid:" + discountgoods.get(j).getDiscount().getGoodsid());
                if (allgoods.get(i).getGid().equals(discountgoods.get(j).getDiscount().getGoodsid())) {
                    allgoods.remove(i);
                }
            }
        }

        request.setAttribute("discountgoods", discountgoods);
        request.setAttribute("goods1", allgoods);
        return "/goods/goods_list";
    }


    /**
     * 收藏商品-根据商品id添加收藏
     *
     * @param request
     * @param
     * @return
     */
    @GetMapping(value = "collectById")
    public String collectById(HttpServletRequest request, String gid, HttpSession session) {

        String phonenum = (String) session.getAttribute("phonenum");//获取当前用户
        List<Collect> collectList = goodsService.getAllcollect(phonenum);//获取到用户对应的收藏

        Collect collectOne = goodsService.getOnecollect(gid);//收藏是否有对应的商品
        if (collectOne != null) {
            return "redirect:/goods/collectAll";
        } else {

            //同过id获得对应的商品
            Goods goods = goodsService.getOnegid(gid);
            ///获取打折后的商品价格
            List<Goods> discountgoods = null;
            if (goods != null) {
                discountgoods = goodsService.getDiscount();
                for (int j = 0; j < discountgoods.size(); j++) {
                    if ((discountgoods.get(j).getDiscount().getGoodsid().equals(gid))) {
                        goods.setGprice(Double.valueOf(discountgoods.get(j).getDiscount().getDiscountnum()) * goods.getGprice());
                    }
                }
            }

            String goodid = goods.getGid();
            String gname = goods.getGname();
            String gremain = goods.getGremain();
            Double gprice = goods.getGprice();

            goodsService.intcollect(phonenum, goodid, gname, gremain, gprice);

            return "redirect:/goods/collectAll";


        }


    }


    /**
     * 查询收藏全部商品
     *
     * @param request
     * @param session
     * @return
     */
    @GetMapping(value = "collectAll")
    public String collectAll(HttpServletRequest request, HttpSession session, Model model) {

        String phonenum = (String) session.getAttribute("phonenum");//获取当前用户


        List<Collect> collectList1 = goodsService.getAllcollect(phonenum);//获取到用户对应的收藏

        request.setAttribute("collect1", collectList1);//没有打折的商品


        return "collect";
    }

        /**
         * 删除收藏，根据id删除收藏商品
         * @param request
         * @param did
         * @return
         */
        @GetMapping(value = "deleteCollect")
        public String deleteCollect (HttpServletRequest request, String did){
            goodsService.deleteCollect(Integer.parseInt(did));
            return "redirect:/goods/collectAll";
        }
    /**
     * 跳转新增地址页面
     * @param request

     * @return
     */
    @GetMapping(value = "createAddress1")
    public String createAddress1 (HttpServletRequest request,HttpSession session){
        return "address";
    }

    /**
     * 新增地址
     * @param request

     * @return
     */
    @GetMapping(value = "createAddress2")
    public String createAddress2 (HttpServletRequest request,
                                  String receivername,
                                  String receiverphone,
                                  String address,
                                  HttpSession session){

        String phonenum = (String) session.getAttribute("phonenum");
        List<Address> addressList=goodsService.getAlladdress(phonenum);
        System.out.println("addressListChange:"+addressList);
       // Address address1= (Address) goodsService.getOneaddress(phonenum);
        if (addressList.size()>0 && addressList!=null){
            goodsService.updateAddress(receivername,receiverphone,address,phonenum);

            return "redirect:/goods/cartAll";
        }else {
            goodsService.insertAddress(receivername, receiverphone ,address ,phonenum);
            return "redirect:/goods/cartAll";
        }

    }
}

