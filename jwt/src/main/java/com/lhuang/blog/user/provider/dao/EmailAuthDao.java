package com.lhuang.blog.user.provider.dao;

import com.lhuang.blog.user.api.pojo.EmailNoAuth;

public interface EmailAuthDao {

    Integer getRecordByUserId(String userId);
    Integer deleteRecordByUserId(String userId);
    Integer insertRecord(EmailNoAuth emailNoAuth);
}
