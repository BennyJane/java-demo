package org.example.com.util;

import java.util.concurrent.TimeUnit;

/**
 * ThreadUtil
 * 1. 正确处理InterruptedException 的sleep方法
 * 2. 正确InterruptedException的处理方法
 */
public class ThreadUtil {
    /**
     * sleep等待, 单位为毫秒, 已捕捉并处理InterruptedException.
     * @param durationMillis durationMillis
     */
    public static void sleep(long durationMillis) {
        try {
            Thread.sleep(durationMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * sleep等待，已捕捉并处理InterruptedException.
     * @param durationMillis value
     * @param unit unit
     */
    public static void sleep(long durationMillis, TimeUnit unit) {
        try {
            Thread.sleep(unit.toMillis(durationMillis));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 纯粹为了提醒下处理InterruptedException的正确方式，除非你是在写不可中断的任务.
     */
    public static void handleInterruptedException() {
        Thread.currentThread().interrupt();
    }
}
