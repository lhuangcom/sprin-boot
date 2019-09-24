package com.lhuang.testparse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestParseApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        redisTemplate.opsForValue().set("123","456");
        System.out.println(redisTemplate.getExpire("123"));
        redisTemplate.expire("123",1000L, TimeUnit.MINUTES);
        System.out.println(redisTemplate.getExpire("123"));
    }

}
