package com.rce.server;


/**
 * @author xuke
 * @create 2024/1/2
 */
class Task {

    private String id;

    private Class<?> taskClazz;

    private long startTime;

    private long endTime;

    private long timeout = 10000;

    private boolean isTimeout = false;

    private Object res;

    public Task() {
    }

    public Task(Class<?> taskClazz) {
        this.taskClazz = taskClazz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class<?> getTaskClazz() {
        return taskClazz;
    }

    public void setTaskClazz(Class<?> taskClazz) {
        this.taskClazz = taskClazz;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public boolean isTimeout() {
        return isTimeout;
    }

    public void setTimeout(boolean timeout) {
        isTimeout = timeout;
    }

    public Object getRes() {
        return res;
    }

    public void setRes(Object res) {
        this.res = res;
    }
}
