package com.lhuang.testparse.controller;


import com.lhuang.testparse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@RestController
public class String_To_Date_Controller {

    @Value("${info.name}")
    private String name;


    @Autowired
    private UserService userService;

    /**
     * 日期转化可以为单个参数配置，也可以全局配置
     * @param date
     * @return
     */
    @RequestMapping("/date")
    public String testDateForm(@RequestParam("date")
         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, HttpSession httpSession){

        System.out.println("打印value值--"+name);
        System.out.println("打印session"+httpSession);

        System.out.println("打印日期字符串---"+date);
        return date.toString();

    }



}
