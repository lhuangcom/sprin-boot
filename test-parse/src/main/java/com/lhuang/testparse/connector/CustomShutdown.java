package com.lhuang.testparse.connector;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lhunag
 * date 2019/9/22
 */
@Slf4j
@Component
public class CustomShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private static final int TIMEOUT = 30;

    private volatile Connector connector;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

        this.connector.pause();

        Executor executor = this.connector.getProtocolHandler().getExecutor();

        if (executor instanceof ThreadPoolExecutor){

            log.warn("WEB 应用准备关闭");
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;

            //初始化一个关闭任务位于当前待处理完毕的任务之后，并拒绝新的任务提交
            threadPoolExecutor.shutdown();
            try {
                if(!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)){
                //等待前一个已提交的任务执行完成后关闭，并且仅在在最大的等待时间内进行；
                    log.warn("WEB 应用等待关闭超过最大时长 "+ TIMEOUT+" 秒，将进行强制关闭！");
                    //尝试强制关闭线程池
                    threadPoolExecutor.shutdownNow();
                    if (!threadPoolExecutor.awaitTermination(TIMEOUT,TimeUnit.SECONDS)){
                        log.error("WEB 应用关闭失败！");
                    }

                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
