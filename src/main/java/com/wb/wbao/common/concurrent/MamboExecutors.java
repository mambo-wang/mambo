package com.wb.wbao.common.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * 双重锁定懒汉式单例实现创建线程池
 */
public class MamboExecutors {

    private static final MamboExecutors instance = new MamboExecutors();

    /** 空闲线程存活时间 */
    private static final int THREAD_IDLE = 30;

    /** 任务队列大小 */
    private static final int THREAD_CAPACITY = 1024;

    public static MamboExecutors get() {
        return instance;
    }

    private volatile ExecutorService mamboService = null;

    private static final Logger logger = LoggerFactory.getLogger(MamboExecutors.class);

    public ExecutorService getMamboService() {
        if(Objects.isNull(this.mamboService)){
            synchronized (this) {
                if(Objects.isNull(this.mamboService)){
                    final int CORE = Runtime.getRuntime().availableProcessors();
                    logger.info("core pool size is {}", CORE);
                    mamboService = new ThreadPoolExecutor(CORE,
                            CORE * 2,
                            THREAD_IDLE, TimeUnit.SECONDS,
                            new LinkedBlockingDeque<>(THREAD_CAPACITY),
                            new DaemonThreadFactory("webPublicService"),
                            new RejectedExecutionHandlerImpl());
                }
            }
        }
        return mamboService;
    }
}
