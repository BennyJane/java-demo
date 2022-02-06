package org.example.com.singleton;

import java.util.concurrent.atomic.AtomicLong;

public class InstanceDemo {

    private static InstanceDemo instance = new InstanceDemo();

    private AtomicLong id = new AtomicLong(0);

    private InstanceDemo() {
    }

    public InstanceDemo getInstance() {
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }

}
