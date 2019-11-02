package com.lhuang.testparse.proxy;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * @author lhunag
 * date 2019/8/20
 */
@Slf4j
public class CglibProxy implements MethodInterceptor {

    public <T> T getProxy(Class<T> tClass){

        return (T) Enhancer.create(tClass,this);

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("动态代理前");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("动态代理后");
        stopWatch.stop();
        log.info("({}ms)", stopWatch.getTotalTimeMillis());
        return result;
    }
}
