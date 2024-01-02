package com.rce.server;

/**
 * @author xuke
 * @create 2024/1/2
 */
class ToJavaClass {

    // 从class二进制流获取Class对象
    public static Class<?> loadClassFromBytes(byte[] classBytes) {
        return new ClassLoader() {
            public Class<?> defineClass(byte[] bytes) {
                return defineClass(null, bytes, 0, bytes.length);
            }
        }.defineClass(classBytes);
    }

}
