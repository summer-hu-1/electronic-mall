package com.itheima.userconsumer.hystrix;

import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.Userorder;
import com.itheima.userconsumer.service.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class OrderServiceHystrix implements OrderService {

    @Override
    public PageInfo<Userorder> getAllOrder(Integer currentPage, Integer limit) {
        return null;
    }

    @Override
    public PageInfo<Userorder> getAllOrderByGName(Integer currentPage, Integer limit, String goodsname) {
        return null;
    }

    @Override
    public int addOrder(Userorder userorder) {
        return 0;
    }

    @Override
    public int editOrder(Userorder userorder) {
        return 0;
    }

    @Override
    public int deleteOrderById(String id) {
        return 0;
    }
}
