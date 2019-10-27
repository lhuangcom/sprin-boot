package com.lhuang.testparse.annotation;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * 通过反射获取被注解标记的方法
 * @author lhunag
 * date 2019/10/27
 */
public class PerssionCheckReflect {

    private static void geMethod(String scanPackage){

        //设置扫描的路径
        Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper
                .forPackage(scanPackage)).setScanners(new MethodAnnotationsScanner()));

        //获取到扫描包内带有@PermissionCheck注解的所有方法集合
        Set<Method> methods = reflections.getMethodsAnnotatedWith(PermissionCheck.class);


        for (Method method : methods){

            //通过反射获取类上的注解
            method.getDeclaringClass().getAnnotation(RequestMapping.class);

            //通过反射获取方法上的注解
            method.getAnnotation(PutMapping.class);

            //获取注解中的某个属性(这里是获取value属性）
            method.getAnnotation(PutMapping.class).value();

            String methodType = "";

            String authUrl = "";
            //获取方法上的@PutMapping,@GetMapping,@PostMapping,@DeleteMapping注解的值，作为请求路径,并区分请求方式
            if (method.getAnnotation(PutMapping.class) != null) {
                methodType = "put";
                if (method.getAnnotation(PutMapping.class).value().length > 0) {
                    authUrl = method.getAnnotation(PutMapping.class).value()[0];
                }
            } else if (method.getAnnotation(GetMapping.class) != null) {
                methodType = "get";
                if (method.getAnnotation(GetMapping.class).value().length > 0) {
                    authUrl = method.getAnnotation(GetMapping.class).value()[0];
                }
            } else if (method.getAnnotation(PostMapping.class) != null) {
                methodType = "post";
                if (method.getAnnotation(PostMapping.class).value().length > 0) {
                    authUrl = method.getAnnotation(PostMapping.class).value()[0];
                }
            } else if (method.getAnnotation(DeleteMapping.class) != null) {
                if (method.getAnnotation(DeleteMapping.class).value().length > 0) {
                    authUrl = method.getAnnotation(DeleteMapping.class).value()[0];
                }
            }



        }
    }

}
