package com.wb.wbao.server.async;

import com.wb.wbao.common.monitor.MonitorMgr;
import com.wb.wbao.common.monitor.TaskMsg;
import com.wb.wbao.common.request.RequestData;
import com.wb.wbao.common.request.RequestMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class AsyncTaskHandler {

    @Resource
    private RequestMgr requestMgr;

    @Resource
    private MonitorMgr monitorMgr;

    private Logger logger = LoggerFactory.getLogger(AsyncTaskHandler.class);

    @PostConstruct
    public void init() {

        requestMgr.register(RequestData.ASYNC_TEST_ONE, this::startAsyncTaskOne);
    }

    public void startAsyncTaskOne (RequestData requestData) {

        try {
            logger.info("now start execute async task one, which type is {}", requestData.getRequestType());

            Future<TaskMsg> future = monitorMgr.postRequest(requestData, 1L);

            if(future.get().getResult() == 0){
                logger.info("async task one success, which type is {}", requestData.getRequestType());
            } else if (future.get().getResult() == 1) {
                logger.error("async task one fail, which type is {}", requestData.getRequestType());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
