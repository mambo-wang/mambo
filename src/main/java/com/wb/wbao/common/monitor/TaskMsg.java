package com.wb.wbao.common.monitor;

import java.io.Serializable;
import java.util.Date;

public class TaskMsg implements Serializable{


    private static final long serialVersionUID = 5038463412470072309L;

    /** 成功 */
    public static final int SUCCESS = 0;

    /** 失败 */
    public static final int FAIL = 1;

    /** 消息id */
    private Long msgId;

    /** 是否完成 */
    private boolean completed;

    /** 执行结果 0成功 1失败 */
    private Integer result;

    /** 进度 */
    private int progress;

    /** 失败原因 */
    private String failMsg;

    /** 开始时间 */
    private Date start;

    /** 结束时间 */
    private Date complete;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getFailMsg() {
        return failMsg;
    }

    public void setFailMsg(String failMsg) {
        this.failMsg = failMsg;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getComplete() {
        return complete;
    }

    public void setComplete(Date complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "TaskMsg{" +
                "msgId=" + msgId +
                ", completed=" + completed +
                ", result=" + result +
                ", progress=" + progress +
                ", failMsg='" + failMsg + '\'' +
                ", start=" + start +
                ", complete=" + complete +
                '}';
    }
}
