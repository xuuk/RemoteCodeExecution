package com.rce.server;

import java.util.Timer;
import java.util.UUID;

/**
 * @author xuke
 * @create 2024/1/2
 */
public class Server implements Runnable {

    private final String id;
    private final int port;

    public Server(int port) {
        this.id = UUID.randomUUID().toString();
        this.port = port;
    }

    @Override
    public void run() {
        ServerThread serverThread = new ServerThread(port, id);
        TaskExecutor taskExecutor = new TaskExecutor(id);
        Timer serverThreadTimer = new Timer();
        Timer taskExecutorTime = new Timer();
        serverThreadTimer.schedule(serverThread, 1, 1000);
        taskExecutorTime.schedule(taskExecutor, 1, 2000);
    }

    public void start() {
        new Thread(this).start();
    }

}
