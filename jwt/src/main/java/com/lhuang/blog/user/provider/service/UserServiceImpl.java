package com.lhuang.blog.user.provider.service;

import com.lhuang.blog.user.api.exception.BaseException;
import com.lhuang.blog.user.api.pojo.EmailNoAuth;
import com.lhuang.blog.user.api.pojo.User;
import com.lhuang.blog.user.api.service.EmailAuthService;
import com.lhuang.blog.user.api.service.EmailService;
import com.lhuang.blog.user.api.service.UserService;
import com.lhuang.blog.user.provider.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailAuthService emailAuthService;

    @Autowired
    private UserDao userDao;



    @Override
    public User register(User user) {

        String uuid = UUID.randomUUID().toString();
        user = Optional.ofNullable(user)
                .map(u->{
                    u.setId(uuid);
                    u.setEmail("2539203552@qq.com");
                    return u;})
                .orElseThrow(() ->new BaseException("传入的参数为null"));

        Integer count = userDao.insertUser(user);

        if (count == 0){
            throw new BaseException("添加用户失败");
        }

        EmailNoAuth emailNoAuth = new EmailNoAuth();
        emailNoAuth.setUserId(uuid);
        count = emailAuthService.addRecord(emailNoAuth);

        if (count == 0){
            throw new BaseException("添加用户邮件认证记录失败");
        }
        return user;
    }

    @Override
    public User login(User user) {
        user = userDao.getUser(user);
        return user;
    }
}
