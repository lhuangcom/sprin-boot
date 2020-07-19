package com.lhuang.testparse.annotation;

import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 动态配置切面值
 *
 * @author lhunag
 * date 2020/1/6
 */
@Configuration
public class ConfigurableAdvisorConfig {

   /* @Value("${pointcut.property}")
    private String pointcut;*/

    @Bean
    public AspectJExpressionPointcutAdvisor configurabledvisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* com.lhuang.testparse.service..*.*(..) ) && @annotation(com.lhuang.testparse.annotation.CustomCache)");
        advisor.setAdvice(new LogAdvice ());
        return advisor;
    }
}
