package com.wb.wbao.common.interceptor;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllInOneInterceptor extends HandlerInterceptorAdapter{

    private NamedThreadLocal<Long> timeThreadLocal = new NamedThreadLocal<>("time");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.currentTimeMillis();
        timeThreadLocal.set(start);
        System.out.println("==========拦截器start time: " + start + "==========");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long end = System.currentTimeMillis();
        long start = timeThreadLocal.get();
        long consumeTime = end-start;
        System.out.println("==============拦截器spend time:" + consumeTime + "========================");
    }
}
