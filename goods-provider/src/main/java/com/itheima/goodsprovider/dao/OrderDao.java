package com.itheima.goodsprovider.dao;

import com.itheima.b2b.commonmodule.model.Userorder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface OrderDao {

    List<Userorder> getAllorder(@Param(value = "phonenum")String phonenum);//查询所有订单

    List<Userorder> getAllorderBygname(@Param(value = "phonenum")String phonenum, @Param(value = "gname")String gname);
}
