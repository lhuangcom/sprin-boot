package com.lhuang.testparse.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public User(){
    }

    public User(String name){
        this.name = name;
    }

}

