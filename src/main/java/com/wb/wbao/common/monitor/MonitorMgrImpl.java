package com.wb.wbao.common.monitor;

import com.wb.wbao.common.concurrent.MamboExecutors;
import com.wb.wbao.common.request.RequestData;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service("monitorMgr")
public class MonitorMgrImpl implements MonitorMgr {


    private ExecutorService cpuBoundService = MamboExecutors.get().getCpuBoundService();

    @Override
    public Future<TaskMsg> postRequest(RequestData requestData, Long msgId) {
        return cpuBoundService.submit(new MonitorProgressTask(requestData, msgId));
    }

    private class MonitorProgressTask implements Callable<TaskMsg> {

        private RequestData requestData;

        private Long msgId;

        public MonitorProgressTask(RequestData requestData, Long msgId) {
            this.requestData = requestData;
            this.msgId = msgId;
        }

        @Override
        public TaskMsg call() throws Exception {

            try {
                TaskMsg result = new TaskMsg();
                result.setStart(new Date());
                int progress = 0;
                while (true) {

                    progress = progress + 10;
                    result.setProgress(progress);

                    if(result.getProgress() == 100){
                        result.setCompleted(true);
                    }

                    if(result.isCompleted()){
                        result.setComplete(new Date());
                        return result;
                    }

                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
