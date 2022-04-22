package org.example.com.practice;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池多线程处理多任务，适用按顺序输出结果
 */
public class P1 {
    public static void main(String[] args) throws InterruptedException {
        P1 p1 = new P1();
        p1.doThing();
    }

    public void doThing() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, 10, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        // TODO 使用有序集合(链表)：保存任务提交顺序
        List<Future<String>> futures = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            Task task = new Task(i);
            // 向线程池，提交任务
            Future<String> res = executor.submit(task);
            // TODO 按照提交顺序保存任务返回Future；不能保证任务按顺序执行
            futures.add(res);
        }

        // 终止线程池
        executor.shutdown();
        // 等待线程池内任务执行完毕
        System.out.println(
                executor.awaitTermination(1, TimeUnit.HOURS)
        );

        // 结果的获取：按照任务提交顺序
        for (int i = 0; i < 10; i++) {
            try {
                String r = futures.get(i).get();
                System.out.println("final:" + r);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }

    private class Task implements Callable {
        private int val;

        public Task(int val) {
            this.val = val;
        }

        @Override
        public Object call() throws Exception {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("done: " + val);
            return "result: " + val;
        }
    }
}
