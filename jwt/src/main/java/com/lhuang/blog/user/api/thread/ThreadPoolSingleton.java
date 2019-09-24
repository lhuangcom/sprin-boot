package com.lhuang.blog.user.api.thread;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author lhunag
 * date 2019/8/19
 */
public enum ThreadPoolSingleton {
    //线程池实例
    INSTANCEE;

    private Logger logger = LoggerFactory.getLogger(ThreadPoolSingleton.class);

    //核心线程池的线程大小
    private final int CORE_POOL_SIZE = 10;

    //线程池的最大线程数量
    private final int MAXIMUM_POOL_SIZE = 20;

    /**
     * 线程池超过线程池最大数量后线程的存活时间
     */
    private final int KEEP_ALIVE_TIME = 3000;

    /**
     * 保存线程任务的阻塞队列
     */
    private BlockingQueue<Runnable> workQueue;

    /**
     * 创建线程的工厂，通过重写可以定义一些关键信息，方便出现问题时可以dump或者通过日志定位问题
     */
    private ThreadFactory threadFactory;

    /**
     * 拒绝策略
     */
    private RejectedExecutionHandler rejectedExecutionHandler;

    private ExecutorService executorService;

    private ThreadPoolSingleton(){
        logger.info("初始化线程池");
        workQueue = new ArrayBlockingQueue<Runnable>(100);
        rejectedExecutionHandler = new CustomRejectStrategy();
        threadFactory = new CustomThreadFactory();
        executorService= new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, workQueue, threadFactory, rejectedExecutionHandler);

    }

    public  ExecutorService getInstance(){
        return executorService;
    }






}
