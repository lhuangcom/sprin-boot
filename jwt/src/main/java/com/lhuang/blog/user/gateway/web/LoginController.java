package com.lhuang.blog.user.gateway.web;

import com.lhuang.blog.user.api.exception.BaseException;
import com.lhuang.blog.user.api.pojo.User;
import com.lhuang.blog.user.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request){
        user = userService.login(user);
        if (user != null){
            request.setAttribute("user",user);
            return "index";
        }
        return "login";
    }

    @RequestMapping("/register")
    public String register(User user,HttpServletRequest request){
        System.out.println(user);
        user = userService.register(user);
        if (user != null){
            request.setAttribute("user",user);
            return "login";
        }
        return "register";
    }


    @RequestMapping("/exception")
    public String testException(){
        throw new BaseException("发生了异常");
    }

}
