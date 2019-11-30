package com.example.demo.incerceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception{
        System.out.println("进入拦截");
        System.out.println(request.getRequestURI());
//        //登陆不做拦截
//        if (request.getRequestURI().equals("login")){
//            System.out.println("200");
//            return true;
//        }
        //验证session 是否存在
        Object obj=request.getSession().getAttribute("username");
        if(obj==null){
            response.sendRedirect("/mas/html/login-zmm/login.html");
            return false;
        }
        return true;
    }
}
