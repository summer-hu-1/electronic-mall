package com.itheima.userprovider.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.Goods;
import com.itheima.b2b.commonmodule.model.User;
import com.itheima.userprovider.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private GoodsDao goodsDao;

    @RequestMapping(value = "/getAllGoods",method = RequestMethod.GET)
    public PageInfo<Goods> getAllGoods(@RequestParam(value = "currentPage")Integer currentPage,
                                       @RequestParam(value = "limit")Integer limit){
        PageHelper.startPage(currentPage,limit);
        List<Goods> userList=goodsDao.getAllGoods();
        PageInfo<Goods> pageInfo=new PageInfo<Goods>(userList);
        return pageInfo;
    }

    @RequestMapping(value = "/getAllGoodsByGName",method = RequestMethod.GET)
    PageInfo<Goods> getAllGoodsByGName(@RequestParam(value = "currentPage")Integer currentPage,
                                     @RequestParam(value = "limit")Integer limit,
                                     @RequestParam(value = "gname")String gname){
        PageHelper.startPage(currentPage,limit);
        List<Goods> userList=goodsDao.getAllGoodsByGName(gname);
        PageInfo<Goods> pageInfo=new PageInfo<Goods>(userList);
        return pageInfo;
    }

    @RequestMapping(value = "/addGoods",method = RequestMethod.POST)
    public int addUser(@RequestBody Goods goods){
        return goodsDao.addGoods(goods);
    }

    @RequestMapping(value = "/editGoods",method = RequestMethod.POST)
    public int editUser(@RequestBody Goods goods){
        return goodsDao.editGoods(goods);
    }

    @RequestMapping(value = "/deleteGoodsById",method = RequestMethod.GET)
    public int deleteUserById(@RequestParam(value = "gid")String gid){
        return goodsDao.deleteGoodsById(gid);
    }

    @RequestMapping(value = "/findGoodsByGid",method = RequestMethod.GET)
    Goods findGoodsByGid(@RequestParam String gid){
        return goodsDao.findGoodsByGid(gid);
    }

    @RequestMapping(value ="/getAllGoodsList",method = RequestMethod.GET)
    List<Goods> getAllGoodsList(){
        return goodsDao.getAllGoods();
    }

    @RequestMapping(value ="/findGoodsByGname",method = RequestMethod.GET)
    Goods findGoodsByGname(@RequestParam String goodsname){
        return goodsDao.findGoodsByGname(goodsname);
    }
}

