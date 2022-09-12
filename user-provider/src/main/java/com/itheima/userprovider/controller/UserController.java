package com.itheima.userprovider.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.User;
import com.itheima.userprovider.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public User login(@RequestParam("phonenum")String phonenum){
        return userDao.login(phonenum);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public int register(@RequestBody User user){
        return userDao.register(user);//注册成功后userDao.register(user)为1
    }

    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
    public PageInfo<User> getAllUser(@RequestParam(value = "currentPage")Integer currentPage,
                                     @RequestParam(value = "limit")Integer limit){
        PageHelper.startPage(currentPage,limit);
        List<User> userList=userDao.getAllUser();
        PageInfo<User> pageInfo=new PageInfo<User>(userList);
        return pageInfo;
    }


    @RequestMapping(value = "/getAllUserByphonenum",method = RequestMethod.GET)
    public PageInfo<User> getAllUserByphonenum(@RequestParam(value = "currentPage")Integer currentPage,
                                               @RequestParam(value = "limit")Integer limit,
                                               @RequestParam(value = "phonenum")String phonenum){
            PageHelper.startPage(currentPage,limit);
            List<User> userList=userDao.getAllUserByphonenum(phonenum);
            PageInfo<User> pageInfo=new PageInfo<User>(userList);
            return pageInfo;
    }


    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public int addUser(@RequestBody User user){
        return userDao.register(user);
    }

    @RequestMapping(value = "/editUser",method = RequestMethod.POST)
    public int editUser(@RequestBody User user){
        return userDao.editUser(user);
    }

    @RequestMapping(value = "/deleteUserById",method = RequestMethod.GET)
    public int deleteUserById(@RequestParam(value = "phonenum")String phonenum){
        return userDao.deleteUserById(phonenum);
    }



}
