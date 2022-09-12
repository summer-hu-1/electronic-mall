package com.itheima.userprovider.dao;


import com.itheima.b2b.commonmodule.model.Goods;
import com.itheima.b2b.commonmodule.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsDao {

    List<Goods> getAllGoods();

    List<Goods> getAllGoodsByGName(String gname);

    int addGoods(Goods goods);

    int editGoods(Goods goods);

    int deleteGoodsById(String gid);

    Goods findGoodsByGid(String gid);

    Goods findGoodsByGname(String goodsname);
}
