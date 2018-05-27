package com.wb.wbao.common.listener;

import org.springframework.context.ApplicationEvent;

/**
 * 事件
 */
public class MyTestEvent extends ApplicationEvent {
    private static final long serialVersionUID = 4334355335453417083L;

    private String msg;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyTestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
