package com.itheima.userprovider.dao;

import com.itheima.b2b.commonmodule.model.Discount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DiscountDao {

    List<Discount> getAllDiscount();

    List<Discount> getAllDiscountByGName(String goodsname);

    int addDiscount(Discount discount);

    int editDiscount(Discount discount);

    int deleteDiscountById(String id);

    Discount findDiscountById(String id);
}

