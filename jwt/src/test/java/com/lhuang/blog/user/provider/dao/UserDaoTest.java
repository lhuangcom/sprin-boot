package com.lhuang.blog.user.provider.dao;

import com.lhuang.blog.user.api.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private  UserDao userDao;

    @Test
    public void getUserById() {
        User user = new User();
        user.setUsername("123");
        System.out.println(userDao.getUserByUsername(user));
    }

    @Test
    public void insertUser() {

        System.out.println(userDao);
        User user = new User();
        user.setId("2");
        user.setUsername("123");
        user.setPassword("123");
        userDao.insertUser(user);
    }
}