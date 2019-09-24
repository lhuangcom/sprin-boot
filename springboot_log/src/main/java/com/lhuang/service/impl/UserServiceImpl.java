package com.lhuang.service.impl;

import com.lhuang.domain.User;
import com.lhuang.mapper.UserMapper;
import com.lhuang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LHuang on 2018/8/20.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> LikeName(String name) {
         return userMapper.LikeName(name);
    }
}
