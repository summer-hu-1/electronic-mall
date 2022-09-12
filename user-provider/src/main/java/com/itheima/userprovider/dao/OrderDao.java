package com.itheima.userprovider.dao;

import com.itheima.b2b.commonmodule.model.Userorder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderDao {
    List<Userorder> getAllOrder();

    List<Userorder> getAllOrderByGName(String goodsname);

    int addOrder(Userorder userorder);

    int editOrder(Userorder userorder);

    int deleteOrderById(String id);
}
