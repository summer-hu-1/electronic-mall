package com.itheima.userprovider.dao;


import com.itheima.b2b.commonmodule.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    //用户登录接口
    User login(@Param(value = "phonenum") String phonenum);

    //注册接口
    int register(User user);//用户注册接口

    //查询全部用户
    List<User> getAllUser();

    //修改用户
    int editUser(User user);

    //根据手机号查询用户
    List<User> getAllUserByphonenum(@Param(value = "phonenum")  String phonenum);

    //删除用户
    int deleteUserById(String phonenum);

}
