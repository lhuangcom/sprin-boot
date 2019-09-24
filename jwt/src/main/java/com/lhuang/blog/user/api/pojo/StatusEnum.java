package com.lhuang.blog.user.api.pojo;

public enum StatusEnum {

    FAIL(0,"fail"),
    SUCCESS(1,"success");

    private int resultCode;

    private String message;

    private StatusEnum(int resultCode,String message){
        this.resultCode = resultCode;
        this.message = message;
    }
}
