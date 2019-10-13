package com.lhuang.blog.user.provider.dao;


import com.lhuang.blog.user.api.pojo.User;


public interface UserDao {

    User getUser(User user);

    Integer getUserByUsername(User user);

    Integer insertUser(User user);
}
