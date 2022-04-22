package org.example.com.features.forkjoinpoll;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class SumTask2 {
    private ForkJoinPool pool;

    public SumTask2() {
        this.pool = new ForkJoinPool();
    }

    public long sumUp(long[] data) {
        long res = pool.invoke(new Task(data, 0, data.length - 1));
        return res;
    }

    public void stop() {
        pool.shutdown();
    }

    //执行任务:
    // RecursiveTask：有返回值
    // RecursiveAction：无返回值
    private static class Task extends RecursiveTask<Long> {

        private long[] data;
        private int from;
        private int to;

        private int limit = 100;

        public Task(long[] data, int from, int to) {
            this.data = data;
            this.from = from;
            this.to = to;
        }

        //此方法为ForkJoin的核心方法：对任务进行拆分  拆分的好坏决定了效率的高低
        @Override
        protected Long compute() {
            if (to - from < limit) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += data[i];
                }
                return total;
            } else {
                // 否则，把任务一分为二，递归拆分(注意此处有递归)到底拆分成多少分 需要根据具体情况而定
                int mid = (from + to) / 2;
                Task leftTk = new Task(data, from, mid);
                Task rightTk = new Task(data, mid, to);
                leftTk.fork();
                rightTk.fork();
                return leftTk.join() + rightTk.join();
            }
        }
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();

        Instant start = Instant.now();

        SumTask2 calculator = new SumTask2();
        long result = calculator.sumUp(numbers);

        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");
        System.out.println("结果为：" + result); // 打印结果500500

        calculator.stop();
    }

}
