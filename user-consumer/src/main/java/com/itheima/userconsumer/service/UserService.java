package com.itheima.userconsumer.service;

import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.User;
import com.itheima.userconsumer.hystrix.UserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname LoginService
 * @Description TODO
 * @Date 2019-9-10 11:29
 * @Created by CrazyStone
 */
@Component
@FeignClient(value = "b2b-user-provider",fallback = UserServiceHystrix.class)
public interface UserService {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public User login(@RequestParam(value = "phonenum")String phonenum);

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public  int register(@RequestBody User user);

    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
    public PageInfo<User> getAllUser(@RequestParam(value = "currentPage")Integer currentPage,
                                 @RequestParam(value = "limit")Integer limit);

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public int addUser(@RequestBody User user);

    @RequestMapping(value = "/editUser",method = RequestMethod.POST)
    public int editUser(@RequestBody User user);

    @RequestMapping(value = "/getAllUserByphonenum",method = RequestMethod.GET)
    public PageInfo<User> getAllUserByphonenum(@RequestParam(value = "currentPage")Integer currentPage,
                                           @RequestParam(value = "limit")Integer limit,@RequestParam(value = "phonenum")String phonenum);

    @RequestMapping(value = "/deleteUserById",method = RequestMethod.GET)
    public int deleteUserById(@RequestParam(value = "phonenum")String phonenum);
}
