package com.lhuang.testparse.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author lhunag
 * date 2019/9/22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EventPublisherTest {

    @Autowired
    private EventPublisher eventPublisher;

    @Test
    public void doLogin() throws InterruptedException {
        Long beginTime = System.currentTimeMillis();
        eventPublisher.doLogin("zenghao@google.com","12345678911");
        System.out.println("处理注册相关业务耗时" + (System.currentTimeMillis() - beginTime )+ "ms");
        System.out.println("处理其他业务逻辑");
        Thread.sleep(500);//模拟处理其他业务请求耗时
        System.out.println("处理所有业务耗时" + (System.currentTimeMillis() - beginTime )+ "ms");
        System.out.println("向客户端发送注册成功响应");
    }
}