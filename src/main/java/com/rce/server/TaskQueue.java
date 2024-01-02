package com.rce.server;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xuke
 * @create 2024/1/2
 */
class TaskQueue {

    private  final Queue<Task> taskQueue = new LinkedList<>();

    public void addTask(Task task) {
        taskQueue.add(task);
    }

    public Task popTask() {
        if (taskQueue.isEmpty()) {
            return null;
        }
        return taskQueue.remove();
    }

    public boolean hasTask() {
        return !taskQueue.isEmpty();
    }

}
