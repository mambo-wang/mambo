package com.wb.wbao.common.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 事件发布者
 */
@Component
public class MyTestEventPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    public void pushListener(String msg){
        applicationContext.publishEvent(new MyTestEvent(this, msg));
    }
}
