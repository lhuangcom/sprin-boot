package com.lhuang.blog.user.api.service;

import com.lhuang.blog.user.api.pojo.EmailNoAuth;

public interface EmailAuthService {

    String sendAntuEmail(String userId);

    Integer validLoginAuth(String userId);

    String processEmailAuth(String token);

    Integer addRecord(EmailNoAuth emailNoAuth);
}
