package com.itheima.goodsconsumer.service;

import com.itheima.b2b.commonmodule.model.*;
import com.itheima.goodsconsumer.hystrix.GoodsServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Component
@FeignClient(value="b2b-goods-provider",fallback = GoodsServiceHystrix.class)
//@FeignClient(value="b2b-goods-provider")
public interface GoodsService {
    @RequestMapping(value = "/getAllgoods",method = RequestMethod.GET)
    public List<Goods> getAllgoods();

    @RequestMapping(value = "/getOnegoods",method = RequestMethod.GET)
    public List<Goods> getOnegoods(@RequestParam(value = "gname") String gname);

    @RequestMapping(value = "/paygoods")
    public int paygoods(@RequestParam(value = "gid") int gid, @RequestParam(value = "gremain") int gremain);

    @RequestMapping(value = "/getAllorder")
    public List<Userorder> getAllorder(@RequestParam(value = "phonenum") String phonenum);

    @RequestMapping(value = "/getOnegid",method = RequestMethod.GET)
    public Goods getOnegid(@RequestParam(value = "gid") String gid);

   @RequestMapping(value = "/insertOrder",method = RequestMethod.POST)
    public int insertOrder(@RequestParam(value = "orderid") String orderid,
                           @RequestParam(value = "goodsname") String goodsname,
                           @RequestParam(value = "number") int number,
                           @RequestParam(value = "price") double price,
                           @RequestParam(value = "phonenum") String phonenum,
                           @RequestParam(value = "receivername") String receivername,
                           @RequestParam(value = "receiverphone") String receiverphone,
                           @RequestParam(value = "address") String address);

   @RequestMapping(value = "/getAllcart",method = RequestMethod.GET)//显示购物车
    public List<Cart> getAllcart(@RequestParam(value = "phonenum") String phonenum);

   @RequestMapping(value = "/getAlladdress",method = RequestMethod.GET)//显示地址
    public List<Address> getAlladdress(@RequestParam(value = "phonenum") String phonenum);


    @RequestMapping(value = "/getOneaddress",method = RequestMethod.GET)//获取一个地址
    public List<Address> getOneaddress(@RequestParam(value = "phonenum") String phonenum);



    @RequestMapping(value = "/getAllcollect",method = RequestMethod.GET)
    public List<Collect> getAllcollect(@RequestParam(value = "phonenum") String phonenum);

   @RequestMapping(value = "/intcart",method = RequestMethod.GET)
    public int intcart(@RequestParam(value = "goodid") String goodid,
                       @RequestParam(value = "goodsname") String goodsname,
                       @RequestParam(value = "goodremain") String goodremain,
                       @RequestParam(value = "number") int number,
                       @RequestParam(value = "price") Double price,
                       @RequestParam(value = "phonenum") String phonenum);

   @RequestMapping(value = "deleteCart" ,method = RequestMethod.GET)
    public int deleteCart(@RequestParam(value = "gid") int gid);

    @RequestMapping(value = "/updateCart",method = RequestMethod.GET)
    public int updateCart(@RequestParam(value = "number") int number, @RequestParam(value = "id") int id);


    @RequestMapping(value = "/updateAddress",method = RequestMethod.GET)
    public int updateAddress(@RequestParam(value = "receivername")String receivername,@RequestParam(value = "receiverphone")String receiverphone,@RequestParam(value = "address")String address,@RequestParam(value = "phonenum")String phonenum);
        //return shoppingDao.updateAddress(receivername,receiverphone,address,phonenum);

    @RequestMapping(value = "/insertAddress",method = RequestMethod.GET)
    public  int insertAddress(@RequestParam(value = "receivername")String receivername,
                              @RequestParam(value = "receiverphone")String receiverphone,
                              @RequestParam(value = "address")String address,
                             @RequestParam(value = "phonenum")String phonenum);

    @RequestMapping(value = "/getDiscount",method = RequestMethod.GET)
    public List<Goods> getDiscount();

    @RequestMapping(value = "/getAllorderBygname")
    public List<Userorder> getAllorderBygname(@RequestParam(value = "phonenum")String phonenum,@RequestParam(value = "gname") String gname);


    @RequestMapping(value = "/intcollect",method = RequestMethod.GET)
    public int intcollect( @RequestParam(value = "phonenum")String phonenum,
                           @RequestParam(value = "goodid")String goodid,
                           @RequestParam(value = "gname")String gname,
                           @RequestParam(value = "gremain")String gremain,
                           @RequestParam(value = "gprice")Double gprice);


    @RequestMapping(value = "deleteCollect" ,method = RequestMethod.GET)
    public int deleteCollect(@RequestParam(value = "goodid") int goodid);


    @RequestMapping(value = "/getOnecollect",method = RequestMethod.GET)
    public Collect  getOnecollect(@RequestParam(value = "goodid") String goodid);

}