package com.liuyan.controller;

import com.liuyan.pojo.Users;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @RequestMapping("/user/index")
    public String add(HttpServletRequest request){
        Users user = (Users) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("userName", user.getName());
        return "/user/index";
    }

    @RequestMapping("/vip/index")
    public String update(){
        return "/vip/index";
    }
}
