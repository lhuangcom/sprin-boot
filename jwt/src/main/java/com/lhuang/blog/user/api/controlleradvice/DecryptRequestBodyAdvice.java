package com.lhuang.blog.user.api.controlleradvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;


/**
 * RequestBodyAdvice可以理解为在@RequestBody之前需要进行的 操作，ResponseBodyAdvice可以理解为在@ResponseBody之后进行的操作
 * ，所以当接口需要加解密时，在使用@RequestBody接收前台参数之前可以先在RequestBodyAdvice的实现类中进行参数的解密，
 * 当操作结束需要返回数据时，可以在@ResponseBody之后进入ResponseBodyAdvice的实现类中进行参数的加密。
 */

/**
 * 请求参数解密
 *
 * @author lhunag
 * date 2020/1/31
 */
@Component
@ControllerAdvice(basePackages = "com.lhuang.blog.user.gateway.web")
@Slf4j
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {


    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> selectedConverterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        String dealData = null;
        try {
            //解密操作
            Map<String,String> dataMap = (Map)body;
            String srcData = dataMap.get("data");
        } catch (Exception e) {
            log.error("异常！", e);
        }
        return dealData;
    }


    @Override
    public Object handleEmptyBody(@Nullable Object var1, HttpInputMessage var2, MethodParameter var3, Type var4, Class<? extends HttpMessageConverter<?>> var5) {
        log.info("3333");
        return var1;
    }


}