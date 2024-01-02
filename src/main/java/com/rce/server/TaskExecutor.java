package com.rce.server;

import com.rce.RemoteInjection;

import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author xuke
 * @create 2024/1/2
 */
class TaskExecutor extends TimerTask {

    private final String id;

    public TaskExecutor(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        Task task = TaskManager.getTask(id);
        if (null == task) {
            return;
        }
        task.setStartTime(System.currentTimeMillis());
        try {
            Class<?> taskClazz = task.getTaskClazz();
            Object taskInstance = taskClazz.getDeclaredConstructor().newInstance();
            if (!(taskInstance instanceof RemoteInjection)) {
                return;
            }

            long timeout = task.getTimeout();

            ExecutorService executorService = Executors.newFixedThreadPool(1);
            Future<Object> future = executorService.submit((RemoteInjection) taskInstance);

            Object res = future.get(timeout, TimeUnit.MILLISECONDS);

            task.setRes(res);

        } catch (Exception e) {
            task.setTimeout(true); // 任务超时
            e.printStackTrace();
        } finally {
            task.setEndTime(System.currentTimeMillis());
            TaskManager.addTaskResult(UUID.randomUUID().toString(), task);
        }

    }
}
