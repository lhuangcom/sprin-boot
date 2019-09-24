package com.lhuang.blog.user.api.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendSimpleMail(String to, String subject, String content);
}
