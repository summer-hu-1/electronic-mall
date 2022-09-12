package com.itheima.goodsprovider.dao;

import com.itheima.b2b.commonmodule.model.Address;
import com.itheima.b2b.commonmodule.model.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Classname ShoppingDao
 * @Description TODO
 * @Date 2019-10-22 17:37
 * @Created by CrazyStone
 */
@Mapper
public interface ShoppingDao {

    List<Cart> getAllcart(@Param(value = "phonenum")String phonenum);//查询所有购物车

    List<Address> getAlladdress(@Param(value = "phonenum")String phonenum);//查询所有地址

    List<Address>  getOneaddress(@Param(value = "phonenum")String phonenum);//查询一个地址




    List<Cart> getAllcollect(@Param(value = "phonenum")String phonenum);//查询所有订单

    int intcart(@Param(value = "goodid")String goodid,
                @Param(value = "goodsname")String goodsname,
                @Param(value = "goodremain")String goodremain,
                @Param(value = "number")int number,
                @Param(value = "price") Double price,
                @Param(value = "phonenum")String phonenum);//插入一条购物车



    int insertAddress(@Param(value = "receivername")String receivername,
                      @Param(value = "address")String address,
                      @Param(value = "phonenum")String phonenum,
                      @Param(value = "receiverphone")String receiverphone
                      );//插入一条地址


    int intcollect(@Param(value = "phonenum")String phonenum,
                   @Param(value = "goodid")String goodid,
                   @Param(value = "gname")String gname,
                   @Param(value = "gremain")String gremain,
                   @Param(value = "gprice")Double gprice);//插入一条收藏

    int updateCart(@Param(value = "number") int number,@Param(value = "id") int id);//修改购物车商品数量

    int updateAddress(@Param(value = "receivername")String receivername,
                    @Param(value = "receiverphone")String receiverphone,
                    @Param(value = "address")String address,
                    @Param(value = "phonenum") String phonenum);//更新地址

}
