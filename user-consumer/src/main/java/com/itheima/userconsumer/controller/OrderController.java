package com.itheima.userconsumer.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.Goods;
import com.itheima.b2b.commonmodule.model.Userorder;
import com.itheima.userconsumer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private  OrderService orderService;

    int row;
    //显示所有订单信息
    @RequestMapping("/getAllOrder")
    public String getAllOrder(Integer currentPage, Integer limit, Model model){
        currentPage = currentPage != null ? currentPage : 1;
        limit = limit != null ? limit : 5;
        PageInfo<Userorder> orderPageInfo =orderService.getAllOrder(currentPage,limit);
        orderPageInfo.getList().get(0).getTime();//???不是很理解
        model.addAttribute("orderPageInfo", orderPageInfo);
        return "/order/order_list";
    }

    //根据订单名称查询订单信息
    @GetMapping("/getAllOrderByGname")
    public String getAllOrderByGname(Integer currentPage, Integer limit, HttpServletRequest request, Model model) {
        String goodsname=request.getParameter("keywords");
        currentPage = currentPage != null ? currentPage : 1;
        limit = limit != null ? limit : 5;
        PageInfo<Userorder> orderPageInfo =orderService.getAllOrderByGName(currentPage,limit,goodsname);
        model.addAttribute("orderPageInfo", orderPageInfo);
        return "/order/order_list";
    }

     /* @RequestMapping("/addOrder")
    public String addOrder(Userorder userorder, HttpServletRequest request) {
        //价钱未int类型 需要修改
        if (userorder != null) {
            row = orderService.addOrder(userorder);
            if (row > 0) {
                request.setAttribute("result", "addSuccess");
            } else {
                request.setAttribute("result", "addError");
            }
        } else {
            request.setAttribute("result", "valueError");
        }
        return "redirect:/order/getAllOrder";
    }


  //修改方法
    @RequestMapping("/editOrder")
    public String editOrder(Userorder userorder, HttpServletRequest request) {
        if (userorder != null) {
            row = orderService.editOrder(userorder);
            if (row > 0) {
                request.setAttribute("result", "editSuccess");
            } else {
                request.setAttribute("result", "editError");
            }
        }else {
            request.setAttribute("result", "valueError");
        }
        return "redirect:/order/getAllOrder";
    }

    //删除方法
    @RequestMapping("deleteOrderById")
    public String deleteOrderById(String id,HttpServletRequest request){
        row=orderService.deleteOrderById(id);
        if (row > 0) {
            request.setAttribute("result", "deleteSuccess");
        } else {
            request.setAttribute("result", "deleteError");
        }
        return "redirect:/order/getAllOrder";
    }*/
}
