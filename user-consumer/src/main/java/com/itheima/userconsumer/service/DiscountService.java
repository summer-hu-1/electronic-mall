package com.itheima.userconsumer.service;


import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.Discount;
import com.itheima.b2b.commonmodule.model.Goods;
import com.itheima.userconsumer.hystrix.DiscountServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Component
@FeignClient(value = "b2b-user-provider",fallback = DiscountServiceHystrix.class)
public interface DiscountService {

    @RequestMapping("/getAllDiscount")
    PageInfo<Discount> getAllDiscount(@RequestParam(value = "currentPage")Integer currentPage,
                                      @RequestParam(value = "limit")Integer limit);


    @RequestMapping("/getAllDiscountByGName")
    PageInfo<Discount> getAllDiscountByGName(@RequestParam(value = "currentPage")Integer currentPage,
                                           @RequestParam(value = "limit")Integer limit,
                                           @RequestParam(value = "goodsname")String goodsname);

    @RequestMapping("/getDiscountAll")
    List<Discount> getDiscountAll();

    @RequestMapping("/addDiscount")
    int addDiscount(@RequestBody Discount discount);

    @RequestMapping("/editDiscount")
    int editDiscount(@RequestBody Discount discount);

    @RequestMapping("/deleteDiscountById")
    int deleteDiscountById(@RequestParam String id);

    @RequestMapping("/findDiscountById")
    Discount findDiscountById(@RequestParam String id);

}
