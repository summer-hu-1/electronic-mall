package com.itheima.b2b.commonmodule.model;

import java.util.Date;


public class Userorder {
    private int id,number;
    private String goodsname;
    private String orderid;
    private Date time;
    private double price;
    private String phonenum;
    private String receivername;
    private String receiverphone;
    private String address;


    @Override
    public String toString() {
        return "Userorder{" +
                "id=" + id +
                ", number=" + number +
                ", goodsname='" + goodsname + '\'' +
                ", orderid='" + orderid + '\'' +
                ", time=" + time +
                ", price=" + price +
                ", phonenum='" + phonenum + '\'' +
                ", receivername='" + receivername + '\'' +
                ", receiverphone='" + receiverphone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }

    public String getReceiverphone() {
        return receiverphone;
    }

    public void setReceiverphone(String receiverphone) {
        this.receiverphone = receiverphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
}
