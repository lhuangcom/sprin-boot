package com.lhuang.blog.user.api.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;//出异常

    public static final int SUCCESS = 1;//正常处理的结果

    public static final int FAIL = 0;//处理失败的结果

    public static final int NO_PERMISSION = 2;

    private T data;
    private String message;
    private Integer resultCode = SUCCESS;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.message = e.toString();
        this.resultCode = FAIL;
    }

}
