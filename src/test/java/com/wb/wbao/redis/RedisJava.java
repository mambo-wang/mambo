package com.wb.wbao.redis;

import redis.clients.jedis.Jedis;

/**
 * Created with IntelliJ IDEA.
 * User: wangbao.
 * Explain:测试RedisPool
 */
public class RedisJava {

    public static void main(String[] args) {
        RedisPool.getJedis().set("name","陈浩翔");
        System.out.println(RedisPool.getJedis().get("name"));
        /** console：
         陈浩翔
         */
    }

}