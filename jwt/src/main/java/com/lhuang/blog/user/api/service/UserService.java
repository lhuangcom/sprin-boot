package com.lhuang.blog.user.api.service;

import com.lhuang.blog.user.api.pojo.User;

public interface UserService {

    User register(User user);

    User login(User user);
}
