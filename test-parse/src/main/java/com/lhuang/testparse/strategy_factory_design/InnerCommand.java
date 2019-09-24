package com.lhuang.testparse.strategy_factory_design;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author LHuang
 * @since 2019/4/16
 */
public interface InnerCommand extends InitializingBean {

    void process(String message);

    /**
     * 实例的命令字符的获取
     * @return
     */
    RequestType getCOMMAND();
}
