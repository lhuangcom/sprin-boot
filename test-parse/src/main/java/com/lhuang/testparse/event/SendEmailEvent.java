package com.lhuang.testparse.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 邮件事件发送源
 * @author lhunag
 * date 2019/9/22
 */
@Getter
public class SendEmailEvent extends ApplicationEvent {

    //定义事件的核心成员：发送目的地，供监听器调用完成邮箱发送功能
    private String emailAddress;

    public SendEmailEvent(Object source,String emailAddress) {
        super(source);
        this.emailAddress = emailAddress;

    }
}
