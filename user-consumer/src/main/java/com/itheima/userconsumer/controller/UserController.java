package com.itheima.userconsumer.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.b2b.commonmodule.model.User;
import com.itheima.userconsumer.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    int row = 0;

    //跳转登录界面
    @GetMapping(value = "/")
    public String login() {
        return "redirect:/admin/tologin";
    }

    //跳转登录界面
    @GetMapping(value = "/admin/tologin")
    public String tologin() { return "login"; }


    // 跳转注册界面
    @GetMapping(value = "/admin/toregister")
    public String toregister() {
        return "register";
    }


    // 用户登录
    @PostMapping(value = "/admin/login")
    public String login(User userLogin, HttpServletRequest request) {
        User user = userService.login(userLogin.getPhonenum());
        if (user != null) {
            if (user.getPassword().equals(userLogin.getPassword())) {
                HttpSession session = request.getSession(true);
                //将登陆者信息存入session
                session.setAttribute("user", user);
                if (user.getCategory().equals("2")) {
                    //如果用户的类别不是管理者，普通用户登录到前台首页
                    return "redirect:http://localhost:8896/goods/getAll?phonenum=" + user.getPhonenum();
                } else {
                    //否则用户为管理员跳转到后台管理的页面
                    //默认登录到所有用户界面
                    //return "redirect:/admin/getAllUser";
                    return "index";
                }
            }
        }
        return "login";
    }

    //用户注册
    @GetMapping(value = "/admin/register")
    public String register(User user) {
        //固定注册一个用户为普通用户
        user.setCategory("2");
        System.out.println("user===" + user);
        if (userService.register(user) > 0) { //注册成功后userService.register(user)为1
            System.out.print("注册成功");
            return "login";
        }
        System.out.print("注册失败");
        return "register";
    }

    //跳转登录界面
    @GetMapping(value = "/admin/welcome")
    public String welcome() { return "welcome"; }

    //查询全部用户
    @GetMapping("/admin/getAllUser")
    public String getAllUser(Integer currentPage, Integer limit, Model model) {
        currentPage = currentPage != null ? currentPage : 1;//如果页码不为空，显示当前页码，为空的话显示第一页
        limit = limit != null ? limit : 5;//如果记录条不为空，显示当前记录条数，为空的话显示5条记录
        PageInfo<User> userPageInfo =userService.getAllUser(currentPage,limit);
        model.addAttribute("userPageInfo", userPageInfo);
        return "/user/user_list";
    }

    //根据手机号查询用户
    @GetMapping("/admin/getAllUserByphonenum")
    public String getAllUserByphonenum(Integer currentPage, Integer limit,HttpServletRequest request, Model model) {
        String phonenum=request.getParameter("keywords");
        currentPage = currentPage != null ? currentPage : 1;
        limit = limit != null ? limit : 5;

        PageInfo<User> userPageInfo = userService.getAllUserByphonenum(currentPage,limit,phonenum);
        model.addAttribute("userPageInfo", userPageInfo);
        return "/user/user_list";
    }
    //新增用户
    @RequestMapping("/admin/addUser")
    public String addUser(User user, HttpServletRequest request) {
        if (user != null) {
            user.setCategory("2");
            row = userService.addUser(user);
            if (row > 0) {
                request.setAttribute("result", "addSuccess");
            } else {
                request.setAttribute("result", "addError");
            }
        } else {
            request.setAttribute("result", "valueError");
        }
        return "redirect:/admin/getAllUser";
    }


    //修改方法
    @RequestMapping("/admin/editUser")
    public String editUser(User user, HttpServletRequest request) {
        if (user != null) {
            row = userService.editUser(user);
            if (row > 0) {
                request.setAttribute("result", "editSuccess");
            } else {
                request.setAttribute("result", "editError");
            }
        }else {
            request.setAttribute("result", "valueError");
        }
        return "redirect:/admin/getAllUser";
    }


    //删除方法
    @RequestMapping("/admin/deleteUserById")
    public String deleteUserById(String phonenum,HttpServletRequest request){
        row=userService.deleteUserById(phonenum);
    if (row > 0) {
        request.setAttribute("result", "deleteSuccess");
    } else {
        request.setAttribute("result", "deleteError");
    }
    return "redirect:/admin/getAllUser";

}

    //增加方法
    @RequestMapping("/admin/useradd")
    public String useradd(){
        return "goods_add";
    }


}