package com.itheima.b2b.commonmodule.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Cart
 * @Description TODO
 * @Date 2019-10-11 18:28
 * @Created by CrazyStone
 */
public class Cart {
    private Integer id;
    private String goodid;
    private String goodsname;
    private String goodremain;
    private Integer number;
    private Double price;
    private String phonenum;
    private List<Goods> goodsList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodremain() {
        return goodremain;
    }

    public void setGoodremain(String goodremain) {
        this.goodremain = goodremain;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGoodid() {
        return goodid;
    }

    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", goodsname='" + goodsname + '\'' +
                ", goodremain='" + goodremain + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", goodid='" + goodid + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", goodsList=" + goodsList +
                '}';
    }
}
