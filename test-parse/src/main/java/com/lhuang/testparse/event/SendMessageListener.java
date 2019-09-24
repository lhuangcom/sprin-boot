package com.lhuang.testparse.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author lhunag
 * date 2019/9/22
 */
@Component
public class SendMessageListener implements ApplicationListener<SendMessageEvent> {

    @Async
    @Override
    public void onApplicationEvent(SendMessageEvent sendMessageEvent) {

        //模拟发送邮短信事件
        System.out.println("正在向" + sendMessageEvent.getPhoneNum()+ "发送短信......");
        try {
            //模拟发送短信过程
            Thread.sleep(1* 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("短信发送成功！");

    }
}
