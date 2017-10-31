package com.wb.wbao.multithread.lock;

import com.wb.wbao.common.concurrent.MamboExecutors;
import com.wb.wbao.server.cache.UserStatusCache;

import java.util.concurrent.ExecutorService;

public class WriteReadLockTest {

    /*
    * 读和写的条件
    * 1、读取：没有线程正在做写操作，且没有线程在请求写操作
    * 2、写入：没有线程正在做读写操作
    *
    * */
    public static void main(String[] args) {

        ExecutorService service = MamboExecutors.get().getMamboService();

        service.execute(() -> UserStatusCache.INSTANCE.put("wbao", 1));

        Thread.currentThread().setDaemon(true);
    }
}
