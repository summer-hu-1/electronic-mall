package com.itheima.userconsumer.service;

import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.Goods;
import com.itheima.b2b.commonmodule.model.Userorder;
import com.itheima.userconsumer.hystrix.GoodsServiceHystrix;
import com.itheima.userconsumer.hystrix.OrderServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "b2b-user-provider",fallback = OrderServiceHystrix.class)
public interface OrderService {

    @RequestMapping(value = "/getAllOrder",method = RequestMethod.GET)
    public PageInfo<Userorder> getAllOrder(@RequestParam(value = "currentPage")Integer currentPage,
                                           @RequestParam(value = "limit")Integer limit);

    @RequestMapping(value = "getAllOrderByGName",method = RequestMethod.GET)
    PageInfo<Userorder> getAllOrderByGName(@RequestParam(value = "currentPage")Integer currentPage,
                                     @RequestParam(value = "limit")Integer limit,
                                     @RequestParam(value = "goodsname")String goodsname);

    @RequestMapping(value = "/addOrder",method = RequestMethod.POST)
    int addOrder(@RequestBody Userorder userorder);

    @RequestMapping(value = "/editOrder",method = RequestMethod.POST)
    int editOrder(@RequestBody Userorder userorder);

    @RequestMapping(value = "/deleteOrderById",method = RequestMethod.GET)
    int deleteOrderById(@RequestParam String id);
}
