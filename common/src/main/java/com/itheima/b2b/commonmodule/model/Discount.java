package com.itheima.b2b.commonmodule.model;


public class Discount {

    private String id;
    private String discountid;
    private String discountnum;
    private String goodsid;
    private String goodsname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscountid() {
        return discountid;
    }

    public void setDiscountid(String discountid) {
        this.discountid = discountid;
    }

    public String getDiscountnum() {
        return discountnum;
    }

    public void setDiscountnum(String discountnum) {
        this.discountnum = discountnum;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id='" + id + '\'' +
                ", discountid='" + discountid + '\'' +
                ", discountnum='" + discountnum + '\'' +
                ", goodsid='" + goodsid + '\'' +
                ", goodsname='" + goodsname + '\'' +
                '}';
    }
}
