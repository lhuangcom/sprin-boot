package com.lhuang.blog.user.provider.dao;

import com.lhuang.blog.user.api.pojo.EmailNoAuth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailAuthDaoTest {

    @Autowired
    private EmailAuthDao emailAuthDao;

    @Test
    public void insertRecord() {
        EmailNoAuth emailNoAuth = new EmailNoAuth();
        emailNoAuth.setUserId("123456");
        emailAuthDao.insertRecord(emailNoAuth);
    }
}