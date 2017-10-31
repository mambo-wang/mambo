package com.wb.wbao.common.concurrent;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory {

    private String threadName = "mamboDaemonThread";

    public DaemonThreadFactory() {
    }

    public DaemonThreadFactory(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Thread newThread(Runnable r) {

        Thread thread = new Thread(r);
        thread.setDaemon(true);
        if(Objects.nonNull(threadName)){
            thread.setName(this.threadName + "-" + thread.getId());
        }
        return thread;
    }
}
