package com.wb.wbao.common.monitor;

import com.wb.wbao.common.request.RequestData;

import java.util.concurrent.Future;

public interface MonitorMgr {


    Future<TaskMsg> postRequest(RequestData requestData, Long msgId);

}
