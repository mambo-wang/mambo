package com.wb.wbao.unitest;

import com.wb.wbao.common.request.RequestData;
import com.wb.wbao.common.request.RequestMgr;
import com.wb.wbao.server.async.AsyncTaskHandler;
import com.wb.wbao.server.session.SessionMgr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestMgrTest {

    @Resource
    private RequestMgr requestMgr;

    @Test
    public void testRequestAndMonitor() {

        RequestData requestData = new RequestData(RequestData.ASYNC_TEST_ONE, "good", null, 1L);

        requestMgr.postRequest(requestData);

        try {
            TimeUnit.SECONDS.sleep(30L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
