package com.itheima.goodsprovider.controller;

import com.itheima.b2b.commonmodule.model.Collect;
import com.itheima.goodsprovider.dao.CartDao;
import com.itheima.goodsprovider.dao.CollectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectController {

    @Autowired
    CollectDao collectDao;

    @RequestMapping(value = "/deleteCollect",method = RequestMethod.GET)
    public int deleteCollect(@RequestParam(value = "goodid")int goodid){
        return collectDao.deleteCollect(goodid);
    }

    @RequestMapping(value = "/getOnecollect",method = RequestMethod.GET)
    public Collect getOnecollect(@RequestParam(value = "goodid")String goodid){
        return collectDao.getOnecollect(goodid);
    }

}
