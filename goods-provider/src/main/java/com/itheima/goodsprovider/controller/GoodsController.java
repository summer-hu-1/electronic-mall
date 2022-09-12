package com.itheima.goodsprovider.controller;

import com.itheima.b2b.commonmodule.model.Goods;
import com.itheima.goodsprovider.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname GoodsController
 * @Description TODO
 * @Date 2019-9-17 18:03
 * @Created by CrazyStone
 */
@RestController
public class GoodsController {
    @Autowired
   GoodsDao goodsDao;

    @RequestMapping(value = "/getAllgoods",method = RequestMethod.GET)
    public List<Goods> getAllgoods(){
    return goodsDao.getAllgoods();
};

    @RequestMapping(value = "/getOnegoods",method = RequestMethod.GET)
    public List<Goods> getOnegoods(@RequestParam(value = "gname")String gname){
    return goodsDao.getOnegoods(gname);
}

    @RequestMapping(value = "/getOnegid",method = RequestMethod.GET)
    public Goods getOnegid(@RequestParam(value = "gid")String gid){
    return goodsDao.getOnegid(gid);
    }

    @RequestMapping(value = "/getDiscount",method = RequestMethod.GET)
    public List<Goods> getDiscount(){
        return goodsDao.getDiscount();
    }
}
