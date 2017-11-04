package com.wb.wbao.common.concurrent;

import java.util.concurrent.Future;

public interface MonitorMgr {


    Future<TaskMsg> postRequest(RequestData requestData, Long msgId);

}
