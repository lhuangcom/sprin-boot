package com.lhuang.blog.user.provider.dao;

import com.lhuang.blog.user.api.pojo.EmailNoAuth;
import com.lhuang.blog.user.api.pojo.GenderEnum;
import com.lhuang.blog.user.api.pojo.User;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import lombok.experimental.var;
import net.bytebuddy.asm.Advice;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private  UserDao userDao;

    @Test
    public void getUserById() {
        User user = new User();
        user.setUsername("1234");
        user.setPassword("123");

        System.out.println(userDao.getUser(user).getExt_json());
    }

    @Test
    public void insertUser() {

        System.out.println(userDao);
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("12345");
        user.setPassword("123");
        user.setSex(GenderEnum.FEMALE);
        EmailNoAuth emailNoAuth = new EmailNoAuth();
        emailNoAuth.setUserId("123");
        user.setExt_json(emailNoAuth);
        userDao.insertUser(user);
    }


    @Test
    public void testLoop(){

        List<List<String>> listList = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        List<String> list1 = new ArrayList<>();
        list1.add("c");
        list1.add("d");
        list1.add("");

        listList.add(list2);
        listList.add(list1);

        List<String> stringList = listList.stream().flatMap(List::stream)
                .filter(letter-> !StringUtils.isEmpty(letter))
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        stringList.forEach(System.out::println);
        System.out.println(stringList.size());





    }
}