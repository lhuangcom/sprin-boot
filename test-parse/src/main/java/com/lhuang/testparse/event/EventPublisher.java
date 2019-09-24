package com.lhuang.testparse.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author lhunag
 * date 2019/9/22
 */
@Component
public class EventPublisher implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void doLogin(String emailAddress,String phoneNum) throws InterruptedException{
        //模拟用户注册的相关业务逻辑处理
        Thread.sleep(200);
        System.out.println("注册成功！");
        //下列向用户发送邮件

        //定义事件
        SendEmailEvent sendEmailEvent = new SendEmailEvent(this,emailAddress);
        SendMessageEvent sendMessageEvent = new SendMessageEvent(this, phoneNum);
        //发布事件
        applicationContext.publishEvent(sendEmailEvent);
        applicationContext.publishEvent(sendMessageEvent);
    }
    //...忽略其他用户管理业务方法
}


class UserService implements ApplicationEventPublisherAware {

    //底层事件发布者
    private ApplicationEventPublisher applicationEventPublisher;

    //通过Set方法完成我们的实际发布者注入
    @Override
    public void setApplicationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void doLogin(String emailAddress,String phoneNum) throws InterruptedException {

        //同上
    }
}

