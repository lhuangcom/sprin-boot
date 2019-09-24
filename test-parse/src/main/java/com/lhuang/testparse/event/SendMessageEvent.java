package com.lhuang.testparse.event;

import org.springframework.context.ApplicationEvent;

/**
 * 短信事件发送源
 * @author lhunag
 * date 2019/9/22
 */
public class SendMessageEvent extends ApplicationEvent {

    private String phoneNum;

    public SendMessageEvent(Object  source, String phoneNum ) {
        super(source);
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
}
