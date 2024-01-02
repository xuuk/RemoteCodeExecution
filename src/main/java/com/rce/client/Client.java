package com.rce.client;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    private final int port;
    private final String host;

    public Client(String host, int port) {
        this.port = port;
        this.host = host;
    }

    public boolean sendTask(String basePath, String clazzName) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compilationResult = compiler.run(null, null, null, "%s%s%s".formatted(basePath, clazzName, ".java"));
        if (0 != compilationResult) {
            return false;
        }
        try {
            Socket socket = new Socket(host, port); // 服务器地址和端口号
            File file = new File("%s%s%s".formatted(basePath, clazzName, ".class"));
            FileInputStream fileInputStream = new FileInputStream(file);
            OutputStream outputStream = socket.getOutputStream();

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            fileInputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}