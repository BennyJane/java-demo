package org.example.com.ThreadPoolDemo;

import java.util.Date;
import java.util.concurrent.ExecutorService;

import static java.lang.Thread.sleep;

public abstract class Task {

    public void runTask(ExecutorService executorService, int count, int interval) {
        for (int i = 0; i < count; i++) {
            final int index = i;
            executorService.execute(() -> {
                System.out.println(new Date() + " " + Thread.currentThread().getName() + " " + index);
                try {
                    sleep(interval * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
