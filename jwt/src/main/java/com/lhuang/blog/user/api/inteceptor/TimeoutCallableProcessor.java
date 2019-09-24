package com.lhuang.blog.user.api.inteceptor;

import com.lhuang.blog.user.api.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;

import java.util.concurrent.Callable;

/**
 * 配置异步处理超时拦截器
 * @author lhunag
 * date 2019/8/19
 */
@Slf4j
public class TimeoutCallableProcessor extends TimeoutCallableProcessingInterceptor {

    @Override
    public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
        log.info("async thread处理超时异常了");
        return new BaseException("超时异常");
    }
}
