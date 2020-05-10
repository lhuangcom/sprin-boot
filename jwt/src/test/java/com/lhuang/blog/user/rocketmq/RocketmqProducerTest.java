package com.lhuang.blog.user.rocketmq;

import org.apache.rocketmq.client.MQHelper;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

/**
 * @author lhunag
 * date 2020/2/4
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RocketmqProducerTest {

    @Autowired
    private RocketmqProducer rocketmqProducer;

    @Test
    public void send()  {

        rocketmqProducer.send();

    }
}