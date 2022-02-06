package org.example.com.singleton;

import java.util.concurrent.atomic.AtomicLong;

public class DoubleCheckDemo {
    // 此处是否需要添加 volatile: 低版本需要，防止指令重排；高版本不需要，JDK内部已经解决。
    private static DoubleCheckDemo instance;

    private AtomicLong id = new AtomicLong(0);

    private DoubleCheckDemo() {
    }

    public DoubleCheckDemo getInstance() {
        if (instance == null) {
            // 在整个类上加锁
            synchronized (DoubleCheckDemo.class) {
                if (instance == null) {
                    instance = new DoubleCheckDemo();
                }
            }
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
