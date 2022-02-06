package org.example.com.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 基于枚举类型的单例实现：
 * 利用Java枚举类型本身的特性，保证实例创建的线程安全与实例的唯一性
 */
public enum EnumDemo {
    INSTANCE;

    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }
}
