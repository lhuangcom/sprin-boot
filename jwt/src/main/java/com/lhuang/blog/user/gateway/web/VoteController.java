package com.lhuang.blog.user.gateway.web;

import com.lhuang.blog.user.api.pojo.ResultBean;
import com.lhuang.blog.user.api.pojo.User;
import com.lhuang.blog.user.api.redis.RedisService;
import com.lhuang.blog.user.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author lhunag
 * date 2019/8/17
 */
@Slf4j
@RestController
public class VoteController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;


    @RequestMapping("/vote")
    public Callable<ResultBean> vote(){


        return new Callable<ResultBean>() {
            @Override
            public ResultBean call() throws Exception {
                User user = new User();
                user.setPassword("100");
                user.setUsername(Thread.currentThread().getName());
                userService.register(user);
                log.info("数据存入成功");
                /*log.info("内部线程：" + Thread.currentThread().getName());
                ResultBean resultBean = new ResultBean();
                resultBean.setData(user);*/
                return null;
            }


        };

    }


    //redis+token 防重提交，字段增加时间，guava的令牌桶限流
    //前台投票请求，直接userid+movieid 存入redis，先判断操作次数，再判断是否多次进入对该电影投票,有效期24.00

    //servlet3.0 异步进行投票操作；前台直接提示是否投票成功
    //redis先判断电影是否存在，不存在则先写数据库，再写redis缓存





}
