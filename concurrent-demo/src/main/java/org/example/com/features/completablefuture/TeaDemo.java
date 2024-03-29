package org.example.com.features.completablefuture;

import java.util.concurrent.*;


// T1Task 需要执行的任务：
// 洗水壶、烧开水、泡茶
class T1Task implements Callable<String> {
    FutureTask<String> ft2;

    // T1 任务需要 T2 任务的 FutureTask
    T1Task(FutureTask<String> ft2) {
        this.ft2 = ft2;
    }

    @Override
    public String call() throws Exception {
        System.out.println("T1: 洗水壶...");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T1: 烧开水...");
        TimeUnit.SECONDS.sleep(15);
        // 获取 T2 线程的茶叶
        String tf = ft2.get();
        System.out.println("T1: 拿到茶叶:" + tf);

        System.out.println("T1: 泡茶...");
        return " 上茶:" + tf;
    }
}

// T2Task 需要执行的任务:
// 洗茶壶、洗茶杯、拿茶叶
class T2Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("T2: 洗茶壶...");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T2: 洗茶杯...");
        TimeUnit.SECONDS.sleep(2);

        System.out.println("T2: 拿茶叶...");
        TimeUnit.SECONDS.sleep(1);
        return " 龙井 ";
    }
}


public class TeaDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        method1();


        // 创建任务 T2 的 FutureTask
        FutureTask<String> ft2
                = new FutureTask<>(new T2Task());
        // 创建任务 T1 的 FutureTask
        FutureTask<String> ft1
                = new FutureTask<>(new T1Task(ft2));
        // 线程 T1 执行任务 ft1
        Thread T1 = new Thread(ft1);
        T1.start();
        // 线程 T2 执行任务 ft2
        Thread T2 = new Thread(ft2);
        T2.start();
        // 等待线程 T1 执行结果
        System.out.println(ft1.get());

    }

    /**
     * 如何获取任务执行结果
     */
    public static void method1() throws ExecutionException, InterruptedException {
        // 实例1
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 2);

        ExecutorService es = Executors.newCachedThreadPool();

        es.submit(futureTask);

        Integer result = futureTask.get();
        System.out.println("result: " + result);
        es.shutdownNow();

        // 实例2
        Thread thread = new Thread(futureTask);
        thread.start();
        Integer result1 = futureTask.get();    // 阻塞，无法继续执行
        System.out.println("result1: " + result1);
        thread.join();
    }
}
