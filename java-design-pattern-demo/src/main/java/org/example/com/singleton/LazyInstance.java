package org.example.com.singleton;

import java.util.concurrent.atomic.AtomicLong;

public class LazyInstance {

    public static LazyInstance instance;

    // 功能
    private AtomicLong id = new AtomicLong(0);

    private LazyInstance() {
    }

    public synchronized LazyInstance getInstance() {
        if (instance == null) {
            instance = new LazyInstance();
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
