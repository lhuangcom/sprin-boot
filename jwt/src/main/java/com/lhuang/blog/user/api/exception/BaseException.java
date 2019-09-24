package com.lhuang.blog.user.api.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {

   public BaseException(String message){
       super(message);
   }

   public BaseException(Throwable throwable){
       super(throwable);
   }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    protected BaseException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
