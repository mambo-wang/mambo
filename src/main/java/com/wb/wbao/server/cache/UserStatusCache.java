package com.wb.wbao.server.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 练习读写锁
 */
public enum UserStatusCache {

    INSTANCE;

    private Map<String, Integer> userStatus = new ConcurrentHashMap<>();

    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    private final Lock readLock = rwl.readLock();

    private final Lock writeLock = rwl.writeLock();

    private Logger logger = LoggerFactory.getLogger(UserStatusCache.class);

    public Integer getStatusByLoginName(String loginName) {

        readLock.lock();
        try {
            logger.info("get status by loginName- {}", loginName);
            TimeUnit.SECONDS.sleep(3);
            return userStatus.get(loginName);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        } finally {
            readLock.unlock();
        }
    }

    public void put(String loginName, Integer status) {

        writeLock.lock();
        try {
            userStatus.put(loginName, status);
            logger.info("put status by loginName {}, status is {}", loginName, status);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

}
