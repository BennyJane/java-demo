package org.example.com.ThreadPoolDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 固定线程池大小
 */
public class createFixedThreadPool extends Task {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CreateCachedThreadPool cachedThreadPool = new CreateCachedThreadPool();
        // 线程最多只有10个
        cachedThreadPool.runTask(executorService, 20, 1);
//        cachedThreadPool.runTask(executorService, 5, 1);
        executorService.shutdown();

        System.out.println(Integer.SIZE);
    }
}
