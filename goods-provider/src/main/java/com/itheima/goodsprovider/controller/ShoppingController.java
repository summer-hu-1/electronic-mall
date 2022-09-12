package com.itheima.goodsprovider.controller;

import com.itheima.b2b.commonmodule.model.Address;
import com.itheima.b2b.commonmodule.model.Cart;
import com.itheima.b2b.commonmodule.model.Collect;
import com.itheima.goodsprovider.dao.ShoppingDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Classname ShoppingController
 * @Description TODO
 * @Date 2019-10-23 9:32
 * @Created by CrazyStone
 */
@RestController
public class ShoppingController {
    @Autowired
    ShoppingDao shoppingDao;


    @RequestMapping(value = "/getAllcart",method = RequestMethod.GET)
    public List<Cart> getAllcart(@RequestParam(value = "phonenum")String phonenum){
        return shoppingDao.getAllcart(phonenum);
    }
    @RequestMapping(value = "/getAlladdress",method = RequestMethod.GET)
    public List<Address> getAlladdress(@RequestParam(value = "phonenum")String phonenum){
        return shoppingDao.getAlladdress(phonenum);
    }
    @RequestMapping(value = "/getOneaddress",method = RequestMethod.GET)
    public List<Address> getOneaddress(@RequestParam(value = "phonenum")String phonenum){
        return shoppingDao.getOneaddress(phonenum);
    }

    @RequestMapping(value = "/updateAddress",method = RequestMethod.GET)
    public int updateAddress(@RequestParam(value = "receivername")String receivername,@RequestParam(value = "receiverphone")String receiverphone,@RequestParam(value = "address")String address,@RequestParam(value = "phonenum")String phonenum){
        return shoppingDao.updateAddress(receivername,receiverphone,address,phonenum);
    }

    @RequestMapping(value = "/getAllcollect",method = RequestMethod.GET)
    public List<Cart> getAllcollect(@RequestParam(value = "phonenum")String phonenum){
        return shoppingDao.getAllcollect(phonenum);
    }
    @RequestMapping(value = "/updateCart",method = RequestMethod.GET)
    public int updateCart(@RequestParam(value = "number")int number, @RequestParam(value = "id")int id){
        return shoppingDao.updateCart(number,id);
    }

    @RequestMapping(value = "/intcart",method = RequestMethod.GET)
    public int intcart(@RequestParam(value = "goodid")String goodid,
                       @RequestParam(value = "goodsname")String goodsname,
                       @RequestParam(value = "goodremain")String goodremain,
                       @RequestParam(value = "number")int number,
                       @RequestParam(value = "price") Double price,
                       @RequestParam(value = "phonenum")String phonenum){
        return shoppingDao.intcart(goodid,goodsname,goodremain,number,price,phonenum);
    }


    @RequestMapping(value = "/insertAddress",method = RequestMethod.GET)
    public int insertAddress(@RequestParam(value = "receivername")String receivername,
                             @RequestParam(value = "receiverphone")String receiverphone,
                             @RequestParam(value = "address")String address,
                             @RequestParam(value = "phonenum")String phonenum){
        return shoppingDao.insertAddress(receivername,address,phonenum,receiverphone);
    }


    @RequestMapping(value = "/intcollect",method = RequestMethod.GET)
    public int intcollect( @RequestParam(value = "phonenum")String phonenum,
                           @RequestParam(value = "goodid")String goodid,
                           @RequestParam(value = "gname")String gname,
                           @RequestParam(value = "gremain")String gremain,
                           @RequestParam(value = "gprice")Double gprice){
        return shoppingDao.intcollect(phonenum,goodid,gname,gremain,gprice);
    }



}
