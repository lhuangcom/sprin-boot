package com.lhuang.controller;

import com.github.pagehelper.PageHelper;
import com.lhuang.domain.User;
import com.lhuang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by LHuang on 2018/8/20.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String getHtml(){
       return "blog";
    }

    @RequestMapping("/likeName")
    public List<User> LikeName(String name){

       PageHelper.startPage(1,1);
       return userService.LikeName(name);
    }
}
