package com.itheima.userconsumer.hystrix;


import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.User;
        import com.itheima.userconsumer.service.UserService;
import org.springframework.stereotype.Component;



@Component
public class UserServiceHystrix implements UserService {
    @Override
    public User login(String phonenum) {
        return null;
    }

    @Override
    public int register(User user) {
        return 0;
    }

    @Override
    public PageInfo<User> getAllUser(Integer currentPage, Integer limit) {
        return null;
    }


    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public int editUser(User user) {
        return 0;
    }

    @Override
    public PageInfo<User> getAllUserByphonenum(Integer currentPage, Integer limit, String phonenum) {
        return null;
    }


    @Override
    public int deleteUserById(String phonenum) {
        return 0;
    }
}
