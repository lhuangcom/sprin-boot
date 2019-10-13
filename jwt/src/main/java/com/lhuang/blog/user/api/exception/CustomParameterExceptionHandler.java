package com.lhuang.blog.user.api.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 处理参数校验的异常控制器
 * @author LHuang
 * @since 2019/7/5
 */
@RestControllerAdvice
public class CustomParameterExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class,
        BindException.class,
        ConstraintViolationException.class,
        ServletRequestBindingException.class})
    public String handleValidationException(Exception e){
        String message;
        //
        if (e instanceof MethodArgumentNotValidException){

            MethodArgumentNotValidException t = (MethodArgumentNotValidException) e;
            message = t.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));

        }else if (e instanceof  BindException){

            BindException t = (BindException) e;
            message = t.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));

        }else if (e instanceof ConstraintViolationException){

            ConstraintViolationException t = (ConstraintViolationException) e;

            message = t.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));

        }else if (e instanceof MissingServletRequestParameterException){

            MissingServletRequestParameterException t = (MissingServletRequestParameterException) e;
            message = t.getParameterName() + "丢失了";

        }else if (e instanceof MissingPathVariableException){

            MissingPathVariableException t = (MissingPathVariableException) e;
            message = t.getVariableName() +"丢失了";

        }else {
            message = "必填参数丢失";
        }

        return message;

    }


}
