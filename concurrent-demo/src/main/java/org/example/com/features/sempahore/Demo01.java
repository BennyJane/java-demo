package org.example.com.features.sempahore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 基于Semaphore实现限流器, 实现并发度控制
 * https://codingw.blog.csdn.net/article/details/119516728
 */
public class Demo01 {
    public static void main(String[] args) {

    }
}

class ErrorVersion {

    public static void main(String[] args) {
        try {
            multiThread();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void baseMethod() {
        Semaphore semaphore = new Semaphore(10);
        try {
            // FIXME 当acquire方法内发生异常退出时，会执行release()方法，造成多释放
//            semaphore.acquire();
            semaphore.tryAcquire(1000, TimeUnit.SECONDS);
            doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private static void doSomething() {
        System.out.println("do ...");
    }

    private static String doSomething(String name) {
        System.out.println("deal with " + name);
        return "done " + name;
    }

    public static void multiThread() throws InterruptedException {
        int count = 100;
        Semaphore semaphore = new Semaphore(10);
        List<String> urls = buildImg(count);
        CountDownLatch cdh = new CountDownLatch(count);

        for (String url : urls) {
            try {
                boolean acquire = semaphore.tryAcquire(3000, TimeUnit.SECONDS);
                if (acquire) {
                    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                            // 需要有返回值
                            () -> doSomething(url)
                    );
                    // CompleteableFuture的whenComplete事件回调函数是发生异常时也会进入，并且第二个参数为异常对象
                    completableFuture.whenComplete((result, t) -> {
                        System.out.println("whenComplete.");
                        if (t != null) {
                            // 处理抛出的异常
                            ((Throwable) t).printStackTrace();
                        } else {
                            // 处理正常流程：结束
                        }
                    });
                    // 释放许可
                    semaphore.release();
                    cdh.countDown();
                }


            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        // 实现方法multiThread体的超时退出功能，因为JDK8的CompletableFuture暂不支持设置超时时间
        // TODO 存在问题：当任务任务没有执行完毕，就超时退出时，会有部分信号量没有得到释放 ==》 延迟释放，如何解决？
        cdh.await(5000, TimeUnit.MICROSECONDS);
    }

    public static List<String> buildImg(int cnt) {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            urls.add("image: " + i);
        }
        return urls;
    }
}


class RightVersion {
    // TODO 代理类
    private static class SemaphoreReleaseOnlyOne {
        private Semaphore semaphore;
        // 基于原子类，实现每个信号量只释放一次的功能
        private AtomicBoolean release = new AtomicBoolean(false);

        public SemaphoreReleaseOnlyOne(Semaphore s) {
            this.semaphore = s;
        }

        public boolean tryAcquire(int time) throws InterruptedException {
            return semaphore.tryAcquire(time, TimeUnit.MICROSECONDS);
        }

        // 释放信号量
        public void release() {
            if (release.compareAndSet(false, true)) {
                semaphore.release();
            }
        }
    }


    public static void multiThread() throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        int limit = 50;
        List<String> urls = buildImg(limit);
        CountDownLatch cdh = new CountDownLatch(limit);
        List<SemaphoreReleaseOnlyOne> releaseOnlyOnes = new ArrayList<>();
        for (String url : urls) {
            try {
                SemaphoreReleaseOnlyOne sro = new SemaphoreReleaseOnlyOne(semaphore);
                releaseOnlyOnes.add(sro);

                boolean acquire = sro.tryAcquire(new Random().nextInt(3000));
                if (acquire) {
                    CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> doSomething(url));
                    cf.whenComplete((res, error) -> {
                        System.out.println("whenComplete");
                        if (error != null) {
                            ((Throwable) error).printStackTrace();
                        } else {
                            System.out.println("success, after do...");
                        }
                    });

                    sro.release();
                    cdh.countDown();
                }

            } catch (Throwable e) {
                e.printStackTrace();
            }
        }


        // 设置阻塞时间
        cdh.await(5000, TimeUnit.MICROSECONDS);

        for (SemaphoreReleaseOnlyOne rso : releaseOnlyOnes) {
            rso.release();
        }
    }


    private static String doSomething(String name) {
        System.out.println("deal with " + name);
        return "done " + name;
    }

    public static List<String> buildImg(int cnt) {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            urls.add("image: " + i);
        }
        return urls;
    }

    public static void main(String[] args) throws InterruptedException {
        RightVersion.multiThread();
    }
}