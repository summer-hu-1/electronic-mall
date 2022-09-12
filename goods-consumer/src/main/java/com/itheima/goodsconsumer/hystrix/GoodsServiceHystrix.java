package com.itheima.goodsconsumer.hystrix;

import com.itheima.b2b.commonmodule.model.*;
import com.itheima.goodsconsumer.service.GoodsService;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class GoodsServiceHystrix implements GoodsService {

    @Override
    public List<Goods> getAllgoods() {
        return null;
    }

    @Override
    public List<Goods> getOnegoods(String gname) {
        return null;
    }

    @Override
    public int paygoods(int gid, int gremain) {
        return 0;
    }

    @Override
    public List<Userorder> getAllorder(String phonenum) {
        return null;
    }

    @Override
    public Goods getOnegid(String gid) {
        return null;
    }

    @Override
    public int insertOrder(String goodsid, String goodsname, int number, double price, String phonenum,
                           String receivername, String receiverphone, String address) {
        return 0;
    }


    @Override
    public List<Cart> getAllcart(String phonenum) {
        return null;
    }

    @Override
    public List<Address> getAlladdress(String phonenum) {
        return null;
    }

    @Override
    public List<Address> getOneaddress(String phonenum) {
        return null;
    }

    @Override
    public List<Collect> getAllcollect(String phonenum) {
        return null;
    }

    @Override
    public int intcart(String goodid, String goodsname, String goodremain, int number, Double price, String phonenum) {
        return 0;
    }


    @Override
    public int deleteCart(int gid) {
        return 0;
    }

    @Override
    public int updateCart(int number, int id) {
        return 0;
    }

    @Override
    public int updateAddress(String receivername, String receiverphone, String address, String phonenum) {
        return 0;
    }

    @Override
    public int insertAddress(String phonenum, String receivername, String receiverphone, String address) {
        return 0;
    }

    @Override
    public List<Goods> getDiscount() {
        return null;
    }

    @Override
    public List<Userorder> getAllorderBygname(String phonenum, String gname) {
        return null;
    }

    @Override
    public int intcollect(String phonenum, String goodid, String gname, String gremain, Double gprice) {
        return 0;
    }

    @Override
    public int deleteCollect(int goodid) {
        return 0;
    }

    @Override
    public Collect getOnecollect(String goodid) {
        return null;
    }

}
