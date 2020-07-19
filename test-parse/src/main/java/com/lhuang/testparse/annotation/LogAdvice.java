package com.lhuang.testparse.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 配合ConfigurableAdvisor 动态拦截指定的方法
 * @author lhunag
 * date 2020/1/6
 */
@Slf4j
public class LogAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("拦截方法");
        CustomCache annotation = methodInvocation.getMethod().getAnnotation(CustomCache.class);
        /*if (Objects.isNull(annotation)){
            return methodInvocation.proceed();
        }*/
        String key = annotation.key();
        log.info("注解里面的key值：{}",key);
        Object[] arguments = methodInvocation.getArguments();
        if (arguments.length <= 0){
            return methodInvocation.proceed();
        }
        Object argument = arguments[0];

        Method getName = argument.getClass().getDeclaredMethod("getName");
        Object invoke = getName.invoke(argument);
        log.info("参数里面的name值：{}",invoke);
        Field[] declaredFields = argument.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);

            if (declaredField.getName().equals("name")){
                String name =(String)declaredField.get(argument);
                log.info("参数里面的name值：{}",name);
            }
        }

        Object result = methodInvocation.proceed();
        return result;
    }
}
