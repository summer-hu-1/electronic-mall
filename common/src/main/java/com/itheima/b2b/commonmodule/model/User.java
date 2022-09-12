package com.itheima.b2b.commonmodule.model;



public class User {
    //private String uaccount,upassword,uname,usex,category;
    //手机号、姓名、国籍和用户类别（1-管理员；2-普通用户）。
    private Integer id;
    private String phonenum,username,password,nationality,category;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phonenum='" + phonenum + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nationality='" + nationality + '\'' +
                ", category='" + category + '\'' +
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
