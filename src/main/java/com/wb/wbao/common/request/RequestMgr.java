package com.wb.wbao.common.request;

public interface RequestMgr {

    void register(int requestType, RequestReceiver requestReceiver);

    Long postRequest(RequestData requestData);

}
