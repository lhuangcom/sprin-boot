package com.lhuang.testparse.strategy_factory_design;

import org.springframework.stereotype.Component;

/**
 * @author LHuang
 * @since 2019/4/16
 */
@Component
public class EchoInfoCommand implements InnerCommand {



    private final RequestType COMMAND = RequestType.ECHOINFO;

    @Override
    public void process(String message) {
        System.out.println("输出消息命令");
    }

    @Override
    public RequestType getCOMMAND() {
        return COMMAND;
    }

    /**
     * 初始化InnerCommandContext集合，可选在工厂方法中在进行初始化
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        InnerCommandContext.instanceMap.put(COMMAND.getMesssage(),this);
    }
}
