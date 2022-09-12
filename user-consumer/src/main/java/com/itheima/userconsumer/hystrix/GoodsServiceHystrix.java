package com.itheima.userconsumer.hystrix;


import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.Goods;
import com.itheima.userconsumer.service.GoodsService;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class GoodsServiceHystrix implements GoodsService {

    @Override
    public PageInfo<Goods> getAllGoods(Integer currentPage, Integer limit) {
        return null;
    }

    @Override
    public PageInfo<Goods> getAllGoodsByGName(Integer currentPage, Integer limit, String gname) {
        return null;
    }

    @Override
    public int addGoods(Goods goods) {
        return 0;
    }

    @Override
    public int editGoods(Goods goods) {
        return 0;
    }

    @Override
    public int deleteGoodsById(String gid) {
        return 0;
    }

    @Override
    public Goods findGoodsByGid(String gid) {
        return null;
    }

    @Override
    public List<Goods> getAllGoodsList() {
        return null;
    }

    @Override
    public Goods findGoodsByGname(String goodsname) {
        return null;
    }
}
