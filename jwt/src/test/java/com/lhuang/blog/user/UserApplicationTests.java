package com.lhuang.blog.user;

import com.lhuang.blog.user.api.exception.BaseException;
import com.lhuang.blog.user.api.pojo.User;
import com.lhuang.blog.user.api.thread.ThreadPoolSingleton;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {

        User user =new User();
        user.setUsername("test");
        user = Optional.ofNullable(user).map(u->{
            u.setUsername("123");
            u.setPassword("123");
            return u;
        }).orElseThrow(()->new BaseException("出错了"));
        System.out.println(user);
    }


    @Test
    public void testThreadPool() throws InterruptedException {
        System.out.println(ThreadPoolSingleton.INSTANCEE.getInstance().submit(()->{
            System.out.println("执行任务了");
        }));

        System.out.println(ThreadPoolSingleton.INSTANCEE.getInstance());

    }

    @Test
    public void testRedisLock(){
        System.out.println(redisTemplate.opsForValue().setIfAbsent("key","value",2,TimeUnit.SECONDS));
        redisTemplate.delete("key");
        System.out.println(redisTemplate.opsForValue().setIfAbsent("key","value",2,TimeUnit.SECONDS));


    }

}

