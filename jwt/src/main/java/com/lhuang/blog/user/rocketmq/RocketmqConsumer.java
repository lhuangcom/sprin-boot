package com.lhuang.blog.user.rocketmq;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
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
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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

        // 每次拉取的间隔，单位为毫秒
        consumer.setPullInterval(2000);
        // 设置每次从队列中拉取的消息数为16
        consumer.setPullBatchSize(16);
        //实现RocketMQ批量消费，单次消费可以消费多条数据
        consumer.setConsumeMessageBatchMaxSize(12);
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Long, String> collect = LongStream.rangeClosed(1, 10).boxed()
                .collect(Collectors.toConcurrentMap(i -> i, v -> UUID.randomUUID().toString(), (o1, o2) -> o1, ConcurrentHashMap::new));

        LongStream.rangeClosed(1, 10).boxed().collect(Collectors.toConcurrentMap(k -> k, v -> new LongAdder(),
                (v1, v2) -> v1, ConcurrentHashMap::new)).computeIfAbsent(1L,v->new LongAdder()).increment();

        int i = ThreadLocalRandom.current().nextInt(100);
        System.out.println(i);
        System.out.println(collect);
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);

        forkJoinPool.execute(()->{
            IntStream.rangeClosed(1,10).parallel().forEach(System.out::println);

        });
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.SECONDS);


        /**
         * CopyOnWriteArrayList 虽然是一个线程安全的 ArrayList，但因为其实现方式是，每次
         * 修改数据时都会复制一份数据出来，所以有明显的适用场景，即读多写少或者说希望无锁读
         * 的场景。
         */

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                ()-> System.out.println("每秒打印1次"),0,1, TimeUnit.SECONDS);


        new ThreadFactoryBuilder().build();

        //如果要配置 Feign 的读取超时，就必须同时配置连接超时，才能生效
        //同时配置 Feign 和 Ribbon 的超时，以 Feign 为准
        //Ribbon Get 请求自动重试1次
    }



}
