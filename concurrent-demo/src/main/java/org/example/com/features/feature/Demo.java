package org.example.com.features.feature;

import org.example.com.util.ThreadUtil;

public class Demo {

    public static void main(String[] args) {
//        Future future = new CompletableFuture();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("runnable...");
//            }
//        };
//        future.cancel(true);
//        FutureTask<String> future1 =
//                new FutureTask<String>(new Callable<String>() {
//                    public String call() {
//                        System.out.println("run....");
//                        return "this is a result.";
//                    }
//                });
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        executor.execute(future1);
//        executor.shutdown();


        StringBuffer sb = new StringBuffer();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadUtil.sleep(20);
                System.out.println("run1...");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadUtil.sleep(10);
                System.out.println("run2...");
            }
        });
        thread1.run();
        thread2.run();
//        thread1.start();
//        thread2.start();

    }
}