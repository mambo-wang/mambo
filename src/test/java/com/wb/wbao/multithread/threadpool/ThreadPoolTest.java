package com.wb.wbao.multithread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    private int i = 0;

    public static void main(String[] args) {

        ThreadPoolTest threadPoolTest = new ThreadPoolTest();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        service.scheduleAtFixedRate(threadPoolTest::test, 0, 1, TimeUnit.SECONDS);

    }

    private void test() {
        System.out.println( i ++);
    }
}
