package com.lhuang.testparse.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author lhunag
 * date 2019/9/22
 */
@Component
public class SendEmailListener implements ApplicationListener<SendEmailEvent> {

    /**
     * 当我们的发布者发布时间时，我们的监听器收到信号，就会调用这个方法
     * 我们对其进行重写来适应我们的需求
     * @param sendEmailEvent
     */
    @Async
    @Override
    public void onApplicationEvent(SendEmailEvent sendEmailEvent) {
        //模拟发送邮件事件
        System.out.println("正在向" + sendEmailEvent.getEmailAddress()+ "发送邮件......");
        try {
            //模拟请求邮箱服务器、验证账号密码，发送邮件耗时。
            Thread.sleep(1* 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("邮件发送成功！");
    }



}
