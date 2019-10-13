package com.lhuang.blog.user.api.pojo;

import lombok.Data;
import lombok.ToString;

@Data
public class User {
    private String id;

    private String username;

    private String password;

    private String email;

    private GenderEnum sex;

    private EmailNoAuth ext_json;



}