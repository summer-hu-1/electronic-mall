package com.itheima.goodsprovider.dao;

import com.itheima.b2b.commonmodule.model.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Classname CartDao
 * @Description TODO
 * @Date 2019-10-23 9:31
 * @Created by CrazyStone
 */
@Mapper
public interface CartDao {

    int deleteCart(@Param(value = "gid") int gid);//根据ID删除订单

    int insertOrder(@RequestParam(value = "orderid") String orderid,
                    @RequestParam(value = "goodsname") String goodsname,
                    @RequestParam(value = "number") int number,
                    @RequestParam(value = "price") double price,
                    @RequestParam(value = "phonenum") String phonenum,
                    @RequestParam(value = "receivername") String receivername,
                    @RequestParam(value = "receiverphone") String receiverphone,
                    @RequestParam(value = "address") String address);//购买商品后添加一条订单数据
}
