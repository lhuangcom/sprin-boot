package com.lhuang.service;

import com.lhuang.domain.User;

import java.util.List;

/**
 * Created by LHuang on 2018/8/20.
 */
public interface UserService {

    public List<User> LikeName(String name);
}
