package org.example.com.ThreadPoolDemo;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;


/**
 * 可缓存的线程池
 * 使用场景：一些存活时间较短的异步任务，默认60s不适用，将中止线程（自动关闭），不会消耗资源
 * 默认情况下，不限制创建线程的数量
 * <p>
 * 当没有空余线程时，会立即创建新的线程，所以如果较短时间内线程数过多，直接报错：Attempt to allocate stack guard pages failed
 * 无法创建新线程
 */
public class CreateCachedThreadPool extends Task {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CreateCachedThreadPool cachedThreadPool = new CreateCachedThreadPool();
        // 创建线程数量小于50
        cachedThreadPool.runTask(executorService, 50, 2);
        // 抛出异常
//        cachedThreadPool.runTask(executorService, 1000, 2);
        executorService.shutdown();
    }
}
