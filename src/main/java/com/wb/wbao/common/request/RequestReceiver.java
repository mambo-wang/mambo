package com.wb.wbao.common.request;

@FunctionalInterface
public interface RequestReceiver {

    /** 处理请求的方法 */
    void handleRequest(RequestData requestData);

}
