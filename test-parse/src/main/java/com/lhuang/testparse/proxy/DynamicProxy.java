package com.lhuang.testparse.proxy;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lhunag
 * date 2019/8/20
 */
public class DynamicProxy implements InvocationHandler {


    private Object target;

    public DynamicProxy(Object target){
        this.target = target;
    }

    public <T> T getProxy(){

        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("代理前");
        Object result = method.invoke(target,args);
        System.out.println("代理后");
        return result;
    }
}
