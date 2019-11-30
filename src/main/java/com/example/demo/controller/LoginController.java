package com.example.demo.controller;

import Temporary.UserLogin;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String addTestName(@RequestBody UserLogin user, BindingResult result,
                              HttpSession session, HttpServletRequest request) throws Exception{

        //        MD5加密
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);
        System.out.println(user.getPassword());
        if(result.hasErrors()){
            return "false";
        }
        //检查用户身份
        UserModel u = userService.checkUser(user);
        if(u!=null){
            session.setAttribute("username", u.getUsername());
            session.setAttribute("password", u.getPassword());
            session.setAttribute("user_id", u.getUserId());
            session.setAttribute("task_id", null);
            return "true";//管理主页
        }
        else{
            return "false";
        }
    }

    @RequestMapping("/verify")
    @ResponseBody
    public Integer getLevel(HttpSession session) throws Exception{
        System.out.println("level获取");
        Object obj= session.getAttribute("username");
        String name = String.valueOf(obj);
        Object obj2 = session.getAttribute("password");
        String password = String.valueOf(obj2);
        UserModel u = userService.findByUserNameAndPassword(name, password);
        if(u!=null){
            return u.getAccessLevel();
        }
        else return null;
    }


    @RequestMapping("/logout")
    @ResponseBody
    public String Logout(HttpSession session) throws Exception{
            System.out.println("logout");
            //session失效
            session.removeAttribute("username");
            session.removeAttribute("password");
            session.removeAttribute("task_id");
            return "true";

    }


}
