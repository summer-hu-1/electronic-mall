package com.itheima.userconsumer.service;

import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.Goods;
import com.itheima.b2b.commonmodule.model.User;
import com.itheima.userconsumer.hystrix.GoodsServiceHystrix;
import com.itheima.userconsumer.hystrix.UserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "b2b-user-provider",fallback = GoodsServiceHystrix.class)
public interface GoodsService {

    @RequestMapping(value = "/getAllGoods",method = RequestMethod.GET)
    public PageInfo<Goods> getAllGoods(@RequestParam(value = "currentPage")Integer currentPage,
                                       @RequestParam(value = "limit")Integer limit);

    @RequestMapping(value = "/getAllGoodsByGName",method = RequestMethod.GET)
    PageInfo<Goods> getAllGoodsByGName(@RequestParam(value = "currentPage")Integer currentPage,
                                     @RequestParam(value = "limit")Integer limit,
                                     @RequestParam(value = "gname")String gname);

    @RequestMapping(value = "/addGoods",method = RequestMethod.POST)
    int addGoods(@RequestBody Goods goods);

    @RequestMapping(value = "/editGoods",method = RequestMethod.POST)
    int editGoods(@RequestBody Goods goods);

    @RequestMapping(value = "/deleteGoodsById",method = RequestMethod.GET)
    int deleteGoodsById(@RequestParam String gid);

    @RequestMapping(value = "/findGoodsByGid",method = RequestMethod.GET)
    Goods findGoodsByGid(@RequestParam String gid);

    @RequestMapping(value ="/getAllGoodsList",method = RequestMethod.GET)
    List<Goods> getAllGoodsList();

    @RequestMapping(value ="/findGoodsByGname",method = RequestMethod.GET)
    Goods findGoodsByGname(@RequestParam String goodsname);
}
