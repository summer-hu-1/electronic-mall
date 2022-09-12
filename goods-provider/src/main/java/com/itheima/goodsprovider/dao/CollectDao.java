package com.itheima.goodsprovider.dao;

import com.itheima.b2b.commonmodule.model.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CollectDao {

    int deleteCollect(@Param(value = "goodid") int goodid);//根据ID删除收藏商品

    Collect getOnecollect(@Param(value = "goodid")String goodid);//根据id查询单个商品详情

}
