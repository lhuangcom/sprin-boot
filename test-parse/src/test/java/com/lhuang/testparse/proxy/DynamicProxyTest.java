package com.lhuang.testparse.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * @author lhunag
 * date 2019/8/20
 */
public class DynamicProxyTest {

    @Test
    public void invoke() {
        DynamicProxy dynamicProxy = new DynamicProxy(new CofferImpl());
        Coffee coffee = dynamicProxy.getProxy();
        coffee.hashCode();
        coffee.drink();
    }

    @Test
    public void intercept() {

        CglibProxy cglibProxy = new CglibProxy();
        Coffee coffee = cglibProxy.getProxy(CofferImpl.class);
        coffee.hashCode();
        //coffee.drink();

    }


}