package org.example.com.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 既保证线程安全，也做到延迟加载
 */
public class StaticInnerClass {
    private AtomicLong id = new AtomicLong(0);

    private StaticInnerClass() {
    }

    // 内部静态类：当外部类被加载时，并不会创建SingletonHolder实例，
    // 只有当调用getInstance()方法时，SingletonHolder才会被加载，此时才会创建instance
    // instance的唯一性、创建过程的线程安全性，都是由JVM保证
    private static class SingletonHolder {
        private static final StaticInnerClass instance = new StaticInnerClass();
    }

    public static StaticInnerClass getInstance() {
        return SingletonHolder.instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
