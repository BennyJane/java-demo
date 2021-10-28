package benny.jane.com.base;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * 创建周期性线程池，支持定时任务以及周期性执行任务
 */
public class createScheduledThreadPool extends Task {
    public static void main(String[] args) {
        // 核心线程设置为3个
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        createScheduledThreadPool cachedThreadPool = new createScheduledThreadPool();
        // 线程最多只有3个
        // 日志间隔2s输出
        cachedThreadPool.runTask(executorService, 10, 2000);
        executorService.shutdown();
    }
    // 使用的时ScheduledExecutorService，而不是 ExecutorService
    public void runTask(ScheduledExecutorService executorService, int count, int interval) {
        for (int i = 0; i < count; i++) {
            final int index = 1;
            // 设置任务延迟时间为3秒
            executorService.schedule(() -> {
                System.out.println(new Date() + " " + Thread.currentThread().getName() + " " + index);
                try {
                    sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 2, TimeUnit.SECONDS);
        }

    }
}
