package com.itheima.userprovider.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.Goods;
import com.itheima.b2b.commonmodule.model.Userorder;
import com.itheima.userprovider.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private  OrderDao orderDao;

    @RequestMapping(value = "/getAllOrder",method = RequestMethod.GET)
    public PageInfo<Userorder> getAllOrder(@RequestParam(value = "currentPage")Integer currentPage,
                                           @RequestParam(value = "limit")Integer limit){
        PageHelper.startPage(currentPage,limit);
        List<Userorder> userList=orderDao.getAllOrder();
        PageInfo<Userorder> pageInfo=new PageInfo<Userorder>(userList);
        return pageInfo;
    }
//根据订单名称查询订单信息
    @RequestMapping(value = "getAllOrderByGName",method = RequestMethod.GET)
    PageInfo<Userorder> getAllOrderByGName(@RequestParam(value = "currentPage")Integer currentPage,
                                         @RequestParam(value = "limit")Integer limit,
                                         @RequestParam(value = "goodsname")String goodsname){
        PageHelper.startPage(currentPage,limit);
        List<Userorder> userList=orderDao.getAllOrderByGName(goodsname);
        PageInfo<Userorder> pageInfo=new PageInfo<Userorder>(userList);
        return pageInfo;
    }

    @RequestMapping(value = "/addOrder",method = RequestMethod.POST)
    int addOrder(@RequestBody Userorder userorder){
        return orderDao.addOrder(userorder);
    }

    @RequestMapping(value = "/editOrder",method = RequestMethod.POST)
    int editOrder(@RequestBody Userorder userorder){
        return orderDao.editOrder(userorder);
    }

    @RequestMapping(value = "/deleteOrderById",method = RequestMethod.GET)
    int deleteOrderById(@RequestParam String id){
        return orderDao.deleteOrderById(id);
    }
}
