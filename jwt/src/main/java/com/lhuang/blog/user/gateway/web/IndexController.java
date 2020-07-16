package com.lhuang.blog.user.gateway.web;


import com.google.common.hash.Hashing;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/monitor")
    @Transactional
    public String monitor(){

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {


        long l = Hashing.murmur3_32().hashString("asikfiuiwueriwueriejarfkls", Charset.defaultCharset()).asInt();
        System.out.println("生成的hash值:"+l);
        long l1 = Hashing.murmur3_32().hashString("asikfiuiwueriwueriejarfkls", Charset.defaultCharset()).asInt();
        System.out.println("生成的hash值:"+l1);

    }



}
