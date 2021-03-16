package com.benny.learning.aopDemo.originjdk;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        ISayHelloWorldImpl sayObj = new ISayHelloWorldImpl();
        AOPHandle handle = new AOPHandle(sayObj);
        ISayHelloWorld proxyObj = (ISayHelloWorld) Proxy.newProxyInstance(
                // 实现类的类加载器， 接口类，代理类对象
                // ClassLoader loader, Class<?> interfaces, InvocationHandler h
                ISayHelloWorldImpl.class.getClassLoader(), new Class[] { ISayHelloWorld.class }, handle);
        proxyObj.say();
    }
}
