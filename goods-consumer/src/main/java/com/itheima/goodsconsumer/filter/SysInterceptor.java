package com.itheima.goodsconsumer.filter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SysInterceptor extends HandlerInterceptorAdapter {
    /**
     * 进入拦截器后首先进入的方法
     * 返回false则不再继续执行
     * 返回true则继续执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)throws Exception
    {
        System.out.println("我是拦截器：我证明我进来了");
        HttpSession session=request.getSession();
       String userInfo = (String) session.getAttribute("phonenum");
        if(userInfo==null)
        {
            System.out.println("我证明用户没有登录");
            response.sendRedirect(request.getContextPath()+"http://localhost:8893/admin/tologin");
            return false;
        } System.out.println("我证明用户已经登录");
        return  true;
    }
}