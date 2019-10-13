package com.lhuang.blog.user.api.pojo;

public enum GenderEnum {

    //男性
    FEMALE(0,"男性"),
    //女性
    MALE(1,"女性"),
    //未知
    UNKNOWN(2,"未知");

    private int value;

    private String description;

    GenderEnum(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int getValue(){
        return this.value;
    }

    public String getDescription(){
        return this.description;
    }

}
