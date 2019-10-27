package com.lhuang.testparse.controller;


import com.lhuang.testparse.api.pojo.User;
import com.lhuang.testparse.event.EventPublisher;
import com.lhuang.testparse.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class TestController{


    @Autowired
    private UserService userService;

    @Autowired
    private EventPublisher eventPublisher;

    @RequestMapping("/testRedis")
    public String testRedis(){

        User user = userService.testRedis();
        System.out.println(user.getName());
        return "123";
    }

    @RequestMapping("/getRedis")
    public String getRedis(){

        System.out.println("你的名字---"+userService.get().getName());

        return "123";
    }

    @RequestMapping("/work")
    public String work() throws InterruptedException{

        log.warn("开始处理业务");
        eventPublisher.doLogin("zenghao@google.com","12345678911");
        log.warn("结束处理业务");
        return "业务完成";


    }

    @PostMapping("/user")
    public String testUser(@RequestBody User user){
        return user.toString();
    }



}
