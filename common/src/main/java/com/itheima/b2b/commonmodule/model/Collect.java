package com.itheima.b2b.commonmodule.model;


import java.util.ArrayList;
import java.util.List;

public class Collect {
    private Integer id;
    private String phonenum;
    private String goodid;
    private String gname;
    private String gremain;
    private Double gprice;
    private List<Goods> goodsList = new ArrayList<>();

    @Override
    public String toString() {
        return "Collect{" +
                "id=" + id +
                ", phonenum='" + phonenum + '\'' +
                ", goodid='" + goodid + '\'' +
                ", gname='" + gname + '\'' +
                ", gremain='" + gremain + '\'' +
                ", gprice=" + gprice +
                ", goodsList=" + goodsList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getGoodid() {
        return goodid;
    }

    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGremain() {
        return gremain;
    }

    public void setGremain(String gremain) {
        this.gremain = gremain;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(double gprice) {
        this.gprice = gprice;
    }


    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
