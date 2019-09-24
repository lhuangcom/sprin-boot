package com.lhuang.testparse.proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lhunag
 * date 2019/8/20
 */
public class CglibProxy implements MethodInterceptor {

    public <T> T getProxy(Class<T> tClass){

        return (T) Enhancer.create(tClass,this);
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("动态代理前");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("动态代理后");
        return result;
    }
}
