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

    /** 公共线程池 */
    private volatile ExecutorService mamboService = null;

    /** I/O 密集型任务线程池 */
    private volatile ExecutorService ioBoundService = null;

    /** CPU 密集型任务线程池 */
    private volatile ExecutorService cpuBoundService = null;

    private static final Logger logger = LoggerFactory.getLogger(MamboExecutors.class);

    public ExecutorService getMamboService() {
        if(Objects.isNull(this.mamboService)){
            synchronized (this) {
                if(Objects.isNull(this.mamboService)){
                    final int CORE = Runtime.getRuntime().availableProcessors();
                    logger.info("mambo service core pool size is {}", CORE);
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

    public ExecutorService getIoBoundService() {
        if(Objects.isNull(this.ioBoundService)){
            synchronized (this) {
                if(Objects.isNull(this.ioBoundService)){
                    final int CORE = Runtime.getRuntime().availableProcessors();
                    logger.info("io bound service core pool size is {}", CORE * 2);
                    ioBoundService = new ThreadPoolExecutor(CORE * 2,//IO密集型，corePoolSize大一点好
                            CORE * 4,
                            THREAD_IDLE, TimeUnit.SECONDS,
                            new LinkedBlockingDeque<>(THREAD_CAPACITY),
                            new DaemonThreadFactory("ioBoundService"),
                            new RejectedExecutionHandlerImpl());
                }
            }
        }
        return ioBoundService;
    }

    public ExecutorService getCpuBoundService() {
        if(Objects.isNull(this.cpuBoundService)){
            synchronized (this) {
                if(Objects.isNull(this.cpuBoundService)){
                    final int CORE = Runtime.getRuntime().availableProcessors();
                    logger.info("cpu bound service core pool size is {}", CORE + 1);
                    cpuBoundService = new ThreadPoolExecutor(CORE + 1,
                            CORE + 1,
                            0L, TimeUnit.SECONDS,//空闲线程立即干掉
                            new LinkedBlockingQueue<>(Integer.MAX_VALUE),//容量巨大，不会满的
                            new DaemonThreadFactory("cpuBoundService"),
                            new RejectedExecutionHandlerImpl());
                }
            }
        }
        return cpuBoundService;
    }

}
