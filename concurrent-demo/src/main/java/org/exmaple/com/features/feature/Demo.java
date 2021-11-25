package org.exmaple.com.features.feature;

import org.apache.ibatis.annotations.Result;

import java.util.concurrent.*;

public class Demo {

    public static void main(String[] args) {
        Future future = new CompletableFuture();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable...");
            }
        };
        future.cancel(true);
        FutureTask<String> future1 =
                new FutureTask<String>(new Callable<String>() {
                    public String call() {
                        System.out.println("run....");
                        return "this is a result.";
                    }
                });
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(future1);
        executor.shutdown();

    }
}
