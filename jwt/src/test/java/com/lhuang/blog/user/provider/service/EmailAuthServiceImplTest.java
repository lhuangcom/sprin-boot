package com.lhuang.blog.user.provider.service;

import com.lhuang.blog.user.api.config.JWTConfig;
import com.lhuang.blog.user.api.service.EmailAuthService;
import com.lhuang.blog.user.provider.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailAuthServiceImplTest {

    @Autowired
    private EmailAuthService emailAuthService;

    @Autowired
    private JWTConfig jwtConfig;

    @Autowired
    private UserDao userDao;

    @Test
    public void sendAntuEmail(){

       /* String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(jwtConfig);*/
        emailAuthService.sendAntuEmail("1");
    }

}