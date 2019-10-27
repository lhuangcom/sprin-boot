package com.lhuang.testparse.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author lhunag
 * date 2019/10/26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    private final Long num = 1000000L;
    private final String SET_KEY="SET:USER:LOGIN:2019082811";
    private final String PF_KEY = "PF:USER:LOGIN:2019082811";


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis() {

        for (int i = 0; i < num;i++ ){
            redisTemplate.opsForHyperLogLog().add(PF_KEY,i);
            redisTemplate.opsForSet().add(SET_KEY,i);
        }

    }
}