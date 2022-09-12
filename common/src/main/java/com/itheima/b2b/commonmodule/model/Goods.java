package com.itheima.b2b.commonmodule.model;

/**
 * @Classname Goods
 * @Description TODO
 * @Date 2019-9-25 11:30
 * @Created by Summer_hu
 */

public class Goods {
    public String gid;
    public String gname, gdetails;
    private Integer types;
    private String gremain;
    private double gprice;

    private  Discount discount;


    public String getGremain() {
        return gremain;
    }

    public void setGremain(String gremain) {
        this.gremain = gremain;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public double getGprice() {
        return gprice;
    }
    public void setGprice(double gprice) {
        this.gprice = gprice;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGdetails() {
        return gdetails;
    }

    public void setGdetails(String gdetails) {
        this.gdetails = gdetails;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", gprice=" + gprice +
                ", gname='" + gname + '\'' +
                ", gdetails='" + gdetails + '\'' +
                ", types=" + types +
                ", gremain='" + gremain + '\'' +
                '}';
    }
}
