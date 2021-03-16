package com.benny.learning.aopDemo.originjdk;

/**
 * 原生JDK实现AOP，切面编程
 */

public class ISayHelloWorldImpl implements ISayHelloWorld {
    @Override
    public String say() {
        System.out.println("Hello World!");
        return "Demo";
    }
}
