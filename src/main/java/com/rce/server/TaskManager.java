package com.rce.server;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuke
 * @create 2024/1/2
 */
class TaskManager {

    private static final Map<String, TaskQueue> allTask = new HashMap<>();

    private static final Map<String, Task> taskResult = new HashMap<>();

    public static void addTask(byte[] classBytes, String id) {
        TaskQueue taskQueue = allTask.get(id);
        if (null == taskQueue) {
            taskQueue = new TaskQueue();
        }
        Class<?> taskClazz = ToJavaClass.loadClassFromBytes(classBytes);
        Task task = new Task(taskClazz);
        taskQueue.addTask(task);
        allTask.put(id, taskQueue);
    }

    public static Task getTask(String id) {
        TaskQueue taskQueue = allTask.get(id);
        if (null != taskQueue && taskQueue.hasTask()) {
            return taskQueue.popTask();
        }
        return null;
    }

    public static Task getTaskResult(String id) {
        return taskResult.get(id);
    }

    public static void addTaskResult(String id, Task task) {
        taskResult.put(id, task);
    }

}
