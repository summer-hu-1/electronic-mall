package com.itheima.userprovider.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.Discount;

import com.itheima.b2b.commonmodule.model.Goods;
import com.itheima.userprovider.dao.DiscountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiscountController {

    @Autowired
    private DiscountDao discountDao;

    @RequestMapping("/getAllDiscount")
    PageInfo<Discount> getAllDiscount(@RequestParam(value = "currentPage")Integer currentPage,
                                      @RequestParam(value = "limit")Integer limit){
        PageHelper.startPage(currentPage,limit);
        List<Discount> userList=discountDao.getAllDiscount();
        PageInfo<Discount> pageInfo=new PageInfo<Discount>(userList);
        return pageInfo;
    }

    @RequestMapping(value ="/toaddGoodspage",method = RequestMethod.GET)
    List<Discount> getAllDiscountList(){
        return discountDao.getAllDiscount();
    }

    @RequestMapping("/getAllDiscountByGName")
    PageInfo<Discount> getAllDiscountByGName(@RequestParam(value = "currentPage")Integer currentPage,
                                           @RequestParam(value = "limit")Integer limit,
                                           @RequestParam(value = "goodsname")String goodsname){
        PageHelper.startPage(currentPage,limit);
        List<Discount> userList=discountDao.getAllDiscountByGName(goodsname);
        PageInfo<Discount> pageInfo=new PageInfo<Discount>(userList);
        return pageInfo;
    }

    @RequestMapping("/getDiscountAll")
    List<Discount> getDiscountAll(){
        return discountDao.getAllDiscount();
    }

    @RequestMapping("/addDiscount")
    int addDiscount(@RequestBody Discount discount){
        return discountDao.addDiscount(discount);
    }

    @RequestMapping("/editDiscount")
    int editDiscount(@RequestBody Discount discount){
        return discountDao.editDiscount(discount);
    }

    @RequestMapping("/deleteDiscountById")
    int deleteDiscountById(@RequestParam String id){
        return discountDao.deleteDiscountById(id);
    }

    @RequestMapping("/findDiscountById")
    Discount findDiscountById(@RequestParam String id){
        return discountDao.findDiscountById(id);
    }

}
