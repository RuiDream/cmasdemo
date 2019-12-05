package com.epic.dpmark.controller;


import com.epic.dpmark.bean.register.RegisterRequest;

import com.epic.dpmark.model.UserModel;
import com.epic.dpmark.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    @ResponseBody
    public String createUser(HttpServletRequest request, @RequestBody RegisterRequest slr) throws Exception{
//        System.out.println(slr.getName());

        //检查用户身份
        boolean u = userService.findUserByName(slr.getName());
//        System.out.println(u);

        if(u==false){
            UserModel userModel=new UserModel();
            userModel.setUsername(slr.getName());

            String md5Password = DigestUtils.md5DigestAsHex(slr.getPassword().getBytes());
            userModel.setPassword(md5Password);
            userModel.setAccessLevel(slr.getAccess());
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        System.out.println(date);
            userModel.setCreateTime(date);
            if(userService.addUser(userModel)){
                return "true";
            }else {
                return "null";
            }

        }
        else{
//            System.out.println("后台测试");
            return "false";
        }




}}
