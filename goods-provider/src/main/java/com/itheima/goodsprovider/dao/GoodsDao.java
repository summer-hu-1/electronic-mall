package com.itheima.goodsprovider.dao;


import com.itheima.b2b.commonmodule.model.Cart;
        import com.itheima.b2b.commonmodule.model.Goods;
        import com.itheima.b2b.commonmodule.model.Userorder;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Param;
        import java.util.List;

/**
 * @Classname GoodsDao
 * @Description TODO
 * @Date 2019-9-17 16:57
 * @Created by CrazyStone
 */
@Mapper
public interface GoodsDao {

    List<Goods> getAllgoods();//查询所有商品

    List<Goods> getOnegoods(@Param(value = "gname")String gname);//根据商品名称模糊查找

    Goods getOnegid(@Param(value = "gid")String gid);//根据id查询单个商品详情

    List<Goods> getDiscount();
}
