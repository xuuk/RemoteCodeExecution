# RemoteCodeExecution 1.0
client可以提交java代码给server执行

**示例**

客户端

```java
package com.rce.client;

/**
 * @author xuke
 * @create 2024/1/2
 */
public class ClientTest {

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 3334);
        client.sendTask("D:\\我的代码\\RemoteCodeExecution\\src\\test\\java\\com\\rce\\client\\", "A");
    }

}

```

服务端

```java
package com.rce.server;

/**
 * @author xuke
 * @create 2024/1/2
 */
public class ServerTest {

    public static void main(String[] args) {
        Server server = new Server(3334);
        server.start();
    }

}

```

提交的代码

```java
package com.rce.client;

import com.rce.RemoteInjection;

/**
 * @author xuke
 * @create 2024/1/2
 */
public class A implements RemoteInjection {

    @Override
    public Object call() throws Exception {
        for (int i = 0; i <10; i++) {
            System.out.println("A");
        }
        return null;
    }

}

```

