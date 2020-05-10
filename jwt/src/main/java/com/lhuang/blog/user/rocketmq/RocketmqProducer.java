package com.lhuang.blog.user.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @author lhunag
 * date 2020/2/2
 */
@Service
@Slf4j
public class RocketmqProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void send(){
        Message message = new Message();
        SendResult sendResult = null ;
        message.setTopic("produce");

        try {
            message.setBody("12313".getBytes(RemotingHelper.DEFAULT_CHARSET));
            DefaultMQProducer producer = rocketMQTemplate.getProducer();
            //发送信息的自动容错机制
            producer.setSendLatencyFaultEnable(Boolean.TRUE);
            log.info("默认的队列数量：{}",producer.getDefaultTopicQueueNums());
            producer.setDefaultTopicQueueNums(2);

            //log.info("生产者信息：{}",producer.fetchPublishMessageQueues("produce"));
            sendResult = producer.send(message);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        log.info("发送信息的结果：{}",sendResult);
    }


}
