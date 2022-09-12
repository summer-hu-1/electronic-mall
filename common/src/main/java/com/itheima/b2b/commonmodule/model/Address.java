package com.itheima.b2b.commonmodule.model;

import java.util.ArrayList;
import java.util.List;

public class Address {
    private Integer id;
    private String phonenum;
    private String receivername;
    private String receiverphone;
    private String address;

    private List<Address> addressList = new ArrayList<>();




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


    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }


    public Address(){};

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", phonenum='" + phonenum + '\'' +
                ", receivername='" + receivername + '\'' +
                ", receiverphone='" + receiverphone + '\'' +
                ", address='" + address + '\'' +
                ", addressList=" + addressList +
                '}';
    }
}
