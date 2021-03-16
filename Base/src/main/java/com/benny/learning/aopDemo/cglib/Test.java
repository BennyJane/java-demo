package com.benny.learning.aopDemo.cglib;

public class Test {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        SayHello proxyImp = (SayHello) proxy.getProxy(SayHello.class);
        proxyImp.say();
    }
}
