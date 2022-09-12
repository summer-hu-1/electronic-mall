package com.itheima.userconsumer.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.Discount;
import com.itheima.b2b.commonmodule.model.Goods;
import com.itheima.b2b.commonmodule.model.Userorder;
import com.itheima.userconsumer.service.DiscountService;
import com.itheima.userconsumer.service.GoodsService;
import com.itheima.userconsumer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("discount")
public class DiscountController {


    @Autowired
    private DiscountService discountService;
    @Autowired
    private GoodsService goodsService;

    int row;


     //显示所有的折扣商品
    @RequestMapping("/getAllDiscount")
    public String getAllOrder(Integer currentPage, Integer limit, Model model) {
        currentPage = currentPage != null ? currentPage : 1;
        limit = limit != null ? limit : 5;
        PageInfo<Discount> discountPageInfo = discountService.getAllDiscount(currentPage, limit);
        model.addAttribute("discountPageInfo", discountPageInfo);
        return "/discount/discount_list"
                ;
    }

    //根据折扣商品的名称查询折扣商品信息
    @GetMapping("/getAllDiscountByGName")
    public String getAllDiscountByGName(Integer currentPage, Integer limit, HttpServletRequest request, Model model) {
        String goodsname = request.getParameter("keywords");
        currentPage = currentPage != null ? currentPage : 1;
        limit = limit != null ? limit : 7;
        PageInfo<Discount> discountPageInfo = discountService.getAllDiscountByGName(currentPage, limit, goodsname);
        model.addAttribute("discountPageInfo", discountPageInfo);
        return "/discount/discount_list";
    }

    //点击新增折扣商品，跳转到新增折扣商品页面
    @RequestMapping("/toaddGoodspage")
    public String toaddGoodspage(Model model) {
        //查询所有的商品
        List<Goods> goodsList = goodsService.getAllGoodsList();
        model.addAttribute("goodsList", goodsList);//用于在discount_add中有个下拉框显示所有的商品

        return "/discount/discount_add";
    }

    //新增折扣商品提交页面
    @RequestMapping("/addDiscount")
    public String addDiscount(Discount discount, HttpServletRequest request, Model model) {
        //如果新增折扣活动不为空，通过折扣活动商品名称查找到对应商品id,再赋值给折扣商品的sid
        if (discount != null) {
            System.out.println(discount.getGoodsname());

            Goods goods = goodsService.findGoodsByGname(discount.getGoodsname());
            discount.setGoodsid(goods.getGid());

            // 查询数据库数据量 + 1，赋值给discountid
            discount.setDiscountid(String.valueOf(discountService.getDiscountAll().size() + 1));

            row = discountService.addDiscount(discount);
            if (row > 0) {
                model.addAttribute("result", "addSuccess");
            } else {

                model.addAttribute("result", "addError");
            }
        } else {
            request.setAttribute("result", "valueError");
        }
        return getAllOrder(1, 5, model);
    }

    //根据折扣活动商品的id对其进行修改
    @RequestMapping("/findDiscountById")
    public String findDiscountById(String id, HttpServletRequest request) {
        Discount discount = discountService.findDiscountById(id);
        System.out.println("discount:" + discount);
        request.setAttribute("discount", discount);
        return "/discount/discount_edit";
    }

    //修改方法
    @RequestMapping("/editDiscount")
    public String editDiscount(Discount discount, HttpServletRequest request) {
        if (discount != null) {
                row = discountService.editDiscount(discount);
                if (row > 0) {
                    request.setAttribute("result", "editSuccess");
                } else {
                    request.setAttribute("result", "editError");
                }
                System.out.println("可以修改");

        }else{
            request.setAttribute("result", "valueError");
        }
        return "redirect:/discount/getAllDiscount";
    }

    //删除方法
    @RequestMapping("deleteDiscountById")
    public String deleteDiscountById(String id, HttpServletRequest request) {
        row = discountService.deleteDiscountById(id);
        if (row > 0) {
            request.setAttribute("result", "deleteSuccess");
        } else {
            request.setAttribute("result", "deleteError");
        }
        return "redirect:/discount/getAllDiscount";
    }
}
