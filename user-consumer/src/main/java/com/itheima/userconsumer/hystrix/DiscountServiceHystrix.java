package com.itheima.userconsumer.hystrix;

import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.Discount;
import com.itheima.userconsumer.service.DiscountService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiscountServiceHystrix implements DiscountService {
    @Override
    public PageInfo<Discount> getAllDiscount(Integer currentPage, Integer limit) {
        return null;
    }

    @Override
    public PageInfo<Discount> getAllDiscountByGName(Integer currentPage, Integer limit, String goodsname) {
        return null;
    }

    @Override
    public List<Discount> getDiscountAll() {
        return null;
    }

    @Override
    public int addDiscount(Discount discount) {
        return 0;
    }

    @Override
    public int editDiscount(Discount discount) {
        return 0;
    }

    @Override
    public int deleteDiscountById(String id) {
        return 0;
    }

    @Override
    public Discount findDiscountById(String id) {
        return null;
    }
}
