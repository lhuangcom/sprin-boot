package com.lhuang.blog.user.gateway.web;

import com.lhuang.blog.user.api.exception.BaseException;
import com.lhuang.blog.user.api.pojo.ResultBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultBean processBaseException(NativeWebRequest request, Exception e) {

        ResultBean result = new ResultBean();
        result.setResultCode(ResultBean.FAIL);
        result.setMessage(e.getMessage());

        return result;
    }
}
