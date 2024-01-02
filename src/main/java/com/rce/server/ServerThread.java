package com.rce.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TimerTask;


/**
 * @author xuke
 * @create 2024/1/2
 */
class ServerThread extends TimerTask {


    private final int port;
    private final String id;

    public ServerThread(int port, String id) {
        this.port = port;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();

                InputStream inputStream = clientSocket.getInputStream();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                byte[] data = byteArrayOutputStream.toByteArray();

                TaskManager.addTask(data, id);

                inputStream.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != serverSocket) {
                        serverSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}