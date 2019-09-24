package com.lhuang.testparse.strategy_factory_design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LHuang
 * @since 2019/4/16
 */
@Component
public class InnerCommandContext implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(InnerCommandContext.class);


    /**
     * 注入实现所有InnerCommand接口的实现类
     * key:service name
     * value: 实例
     */
    @Autowired
    private Map<String,InnerCommand> commandMap = new HashMap<>();


    /**
     * service实例的map集合
     */
    public static Map<String,InnerCommand> instanceMap = new HashMap<>();


    public static InnerCommand getInstance(String message){

        InnerCommand innerCommand = instanceMap.get(message);
        if (innerCommand == null){
            logger.info("命令无效");
        }
        return innerCommand;
    }


    /**
     * 自动注册instanceMap集合
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        for (Map.Entry<String,InnerCommand> entry : commandMap.entrySet()){
            String message = entry.getValue().getCOMMAND().getMesssage();
            instanceMap.put(message,entry.getValue());
            System.out.println(entry.getKey()+""+ message+"-----"+instanceMap.size());
        }
    }
}
