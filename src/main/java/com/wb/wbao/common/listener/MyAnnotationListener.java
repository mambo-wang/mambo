package com.wb.wbao.common.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyAnnotationListener {

    /**
     * 异步执行监听，需要在Application类中添加@EnableAsync注解
     * @param event 事件
     */
    @Async
    @EventListener
    public void listener(MyTestEvent event){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("============注解监听器:" + event.getMsg());
    }
}
