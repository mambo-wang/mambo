package com.wb.wbao.common.request;

import com.wb.wbao.common.concurrent.MamboExecutors;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;

@Service("requestMgr")
public class RequestMgrImpl implements RequestMgr {

    Logger logger = LoggerFactory.getLogger(RequestMgrImpl.class);

    private ExecutorService ioService = MamboExecutors.get().getIoBoundService();

    private Map<Integer, Set<RequestReceiver>> listeners = new ConcurrentHashMap<>();

    @Override
    public void register(int requestType, RequestReceiver requestReceiver) {

        Set<RequestReceiver> tasks = new CopyOnWriteArraySet<>();
        tasks.add(requestReceiver);
        listeners.put(requestType, tasks);
        logger.info("add an listener");

//        Optional<Set<RequestReceiver>> receivers = Optional.ofNullable(listeners.get(requestType));
//
//        receivers.ifPresent(requestReceivers -> {
//            requestReceivers.add(requestReceiver);
//            listeners.put(requestType, requestReceivers);
//            logger.info("add an listener");
//        });

    }

    @Override
    public Long postRequest(RequestData requestData) {

        int requestType = requestData.getRequestType();

        Set<RequestReceiver> receivers = listeners.get(requestType);

        if(CollectionUtils.isEmpty(receivers)) {
            logger.info("empty listner");
            return null;
        }

        /** TODO 推送前台 */
        Long msgId = requestData.isSendWebMsg() ? 1L : null;

        try {
            receivers.forEach(task -> ioService.execute(() -> task.handleRequest(requestData)));
        } catch (Exception e) {
            logger.info("exception show {}", e);
        }

        return msgId;
    }
}
