package com.itheima.goodsprovider.controller;

import com.itheima.b2b.commonmodule.model.Cart;
import com.itheima.goodsprovider.dao.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CartController {
    @Autowired
    CartDao cartDao;

    @RequestMapping(value = "/deleteCart",method = RequestMethod.GET)
    public int deleteCart(@RequestParam(value = "gid")int gid){
        return cartDao.deleteCart(gid);
    }

    @RequestMapping(value = "/insertOrder",method = RequestMethod.POST)
    public int insertOrder(@RequestParam(value = "orderid") String orderid,
                           @RequestParam(value = "goodsname") String goodsname,
                           @RequestParam(value = "number") int number,
                           @RequestParam(value = "price") double price,
                           @RequestParam(value = "phonenum") String phonenum,
                           @RequestParam(value = "receivername") String receivername,
                           @RequestParam(value = "receiverphone") String receiverphone,
                           @RequestParam(value = "address") String address) {

        return cartDao.insertOrder(orderid, goodsname, number, price, phonenum, receivername, receiverphone, address);
    }
}
