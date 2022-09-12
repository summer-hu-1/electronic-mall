package com.itheima.userconsumer.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.Goods;
import com.itheima.userconsumer.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    int row;

    //查询全部用户
    @GetMapping("/getAllGoods")
    public String getAllGoods(Integer currentPage, Integer limit, Model model) {
        currentPage = currentPage != null ? currentPage : 1;
        limit = limit != null ? limit : 5;
        PageInfo<Goods> goodsPageInfo =goodsService.getAllGoods(currentPage,limit);
        model.addAttribute("goodsPageInfo", goodsPageInfo);
        return "/goods/goods_list";
    }

    //根据商品名称查询商品
    @GetMapping("/getAllGoodsByGName")
    public String getAllGoodsByGName(Integer currentPage, Integer limit, HttpServletRequest request, Model model) {
        String gname=request.getParameter("keywords");
        currentPage = currentPage != null ? currentPage : 1;
        limit = limit != null ? limit : 5;
        PageInfo<Goods> goodsPageInfo =goodsService.getAllGoodsByGName(currentPage,limit,gname);
        model.addAttribute("goodsPageInfo", goodsPageInfo);
        return "/goods/goods_list";
    }

    //点击新增商品跳到新增商品页面
    @RequestMapping("/toaddGoodspage")
    public String toaddGoodspage() {
        return "/goods/goods_add";
    }

    //新增商品页面，有表单提交的逻辑
    @RequestMapping("/addGoods")
    public String addGoods(Goods goods, HttpServletRequest request,@RequestParam("file") MultipartFile file) throws IOException {
        //价钱未int类型 需要修改
//        System.out.println("图片："+file.getOriginalFilename());
//        String path = "C:\\Users\\24034\\Desktop\\电子商城\\electronic-mall\\goods-consumer\\src\\main\\resources\\static\\img\\goods";
//        File files = new File(path,file.getOriginalFilename());//返回客户端文件系统中的原始文件名+路径
//        File parentFile = files.getParentFile();//获取实例对象的父项的实例对象
//        if (!parentFile.exists()){//如果不存在父目录
//            parentFile.mkdir();//创建目录
//        }
//        file.transferTo(files);//保存上传的文件

        goods.setGremain("/img/goods/"+file.getOriginalFilename());//设置商品的Gremain字段

        if (goods != null) {
            // 判断当前输入内容是否存在gid
            if(goods.gid == null || goods.gid == ""){
                // 获取当前数据长度，进行新gid内容赋值
                //方便商品查询，商品管理
                int rows = goodsService.getAllGoodsList().size();
                goods.gid = String.valueOf(rows + 1);
            }
            row = goodsService.addGoods(goods);
            if (row > 0) {
                request.setAttribute("result", "addSuccess");
            } else {
                request.setAttribute("result", "addError");
            }
        } else {
            request.setAttribute("result", "valueError");
        }
        return "redirect:/goods/getAllGoods";
    }

    //点击对应的商品，跳转到修改商品信息页面，信息回显
    @RequestMapping("/findGoodsByGid")
    public String findGoodsByGid(String gid, HttpServletRequest request) {
        Goods goods=goodsService.findGoodsByGid(gid);
        System.out.println("goods:"+goods);
        request.setAttribute("goods",goods);
        return "/goods/goods_edit";
    }

    //提交修改商品方法
    @RequestMapping("/editGoods")
    public String editGoods(Goods goods, HttpServletRequest request) {
        if (goods != null) {
            row = goodsService.editGoods(goods);
            if (row > 0) {
                request.setAttribute("result", "editSuccess");
            } else {
                request.setAttribute("result", "editError");
            }
        }else {
            request.setAttribute("result", "valueError");
        }
        return "redirect:/goods/getAllGoods";
    }

    //删除方法
    @RequestMapping("/deleteGoodsById")
    public String deleteGoodsById(String gid,HttpServletRequest request){
        row=goodsService.deleteGoodsById(gid);
        if (row > 0) {
            request.setAttribute("result", "deleteSuccess");
        } else {
            request.setAttribute("result", "deleteError");
        }
        return "redirect:/goods/getAllGoods";
    }

}
