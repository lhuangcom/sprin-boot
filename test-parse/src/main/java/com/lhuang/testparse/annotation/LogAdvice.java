package com.lhuang.testparse.annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 配合ConfigurableAdvisor 动态拦截指定的方法
 * @author lhunag
 * date 2020/1/6
 */
public class LogAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object result = methodInvocation.proceed();
        return result;
    }
}
