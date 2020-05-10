package com.lhuang.blog.user.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.PullTaskCallback;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhunag
 * date 2020/2/4
 */
@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "consumer-group",topic = "produce")
public class RocketmqConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener, MessageListenerConcurrently {


    @Override
    public void onMessage(String message) {

        log.info("需要消费的消息：{}",message);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {

        DefaultMQPullConsumer defaultMQPullConsumer = new DefaultMQPullConsumer();
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer();
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
