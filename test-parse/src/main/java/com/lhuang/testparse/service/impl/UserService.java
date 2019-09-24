package com.lhuang.testparse.service.impl;


import com.lhuang.testparse.api.pojo.User;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@CacheConfig(cacheNames = "redis_cache1")
@Service
public class UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Cacheable
    public User testRedis(){

        User user = new User(UUID.randomUUID().toString());

        //redisTemplate.opsForValue().set("user",user);

        return user;
    }

    public User get(){
        User user = testRedis();
     //  User user = (User) redisTemplate.opsForValue().get("user");
       return user;
    }

    /**
     * 使用lettuce作为redis操作api
     * 2.0后redisTemplate是对lettuce的封装底层
     */
    public void useLettuce(){
        RedisClient client = RedisClient.create("redis://localhost");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisStringCommands sync = connection.sync();
        String value =(String) sync.get("key");
        connection.close();
        client.shutdown();
    }
}
