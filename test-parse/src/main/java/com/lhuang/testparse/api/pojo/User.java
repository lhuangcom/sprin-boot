package com.lhuang.testparse.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author asus
 */
@Data
public class User implements Serializable {
    private String name;
    public User(){
    }

    public User(String name){
        this.name = name;
    }

}

