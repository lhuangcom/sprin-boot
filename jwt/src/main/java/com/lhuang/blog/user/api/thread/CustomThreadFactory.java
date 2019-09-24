package com.lhuang.blog.user.api.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ThreadFactory;

/**
 *
 * @author LHuang
 * @since 2019/6/6
 */
@Slf4j
public class CustomThreadFactory implements ThreadFactory {

    private ArrayList<String> stats = new ArrayList<>();

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        //r实例会被封装成代理类Worker
        String logInfo = String.format("Created thread %d with name %s on%s\n", t.getId(), t.getName(), new Date());
        stats.add(logInfo);
        log.debug(logInfo);
        return t;
    }

    /**
     *    这个方法的调用时机 就看你们具体业务上需求如何了，其实线程工厂真的很简单，主要就是根据你的环境
     *     定制出你需要的信息 方便日后调试即可 不需要太纠结。
     */
    public String getStas() {
        StringBuffer buffer = new StringBuffer();
        Iterator<String> it = stats.iterator();
        while (it.hasNext()) {
            buffer.append(it.next());
        }
        return buffer.toString();
    }

}