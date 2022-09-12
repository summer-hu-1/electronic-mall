package com.itheima.goodsprovider.controller;

import com.itheima.b2b.commonmodule.model.Userorder;
import com.itheima.goodsprovider.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderDao orderDao;
    @RequestMapping(value = "/getAllorder",method = RequestMethod.GET)
    public List<Userorder> getAllorder(@RequestParam(value = "phonenum")String phonenum){
        return orderDao.getAllorder(phonenum);
    }

    @RequestMapping(value = "/getAllorderBygname")
    public List<Userorder> getAllorderBygname(@RequestParam(value = "phonenum")String phonenum,@RequestParam(value = "gname") String gname){
        return orderDao.getAllorderBygname(phonenum,gname);
    }
}
