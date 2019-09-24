package com.lhuang.blog.user.provider.service;

import com.lhuang.blog.user.api.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceImplTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendSimpleMail() {
        emailService.sendSimpleMail("2798600858@qq.com","test send mail","this is a simple mail");
    }
}