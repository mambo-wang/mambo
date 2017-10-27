package com.wb.wbao.multithread;

import com.wb.wbao.server.cache.UserStatusCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WriteReadLockTest {

    public static void main(String[] args) {

//        UserStatusCache.INSTANCE.put("wbao", 1);

        ExecutorService service = Executors.newFixedThreadPool(6);

        System.out.println("start");
        service.submit(() -> UserStatusCache.INSTANCE.put("wbao", 1));
        service.submit(() -> UserStatusCache.INSTANCE.getStatusByLoginName("wbao"));




    }
}
