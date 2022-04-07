package org.example.com.volatileDemo;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class Demo1 {
    public static volatile int sum = 0;
    public static int sum1 = 0;

    public static void main(String[] args) {
//        method1();
        method2();
    }

    /**
     * 三个线程结果值：均比10000大，但都只是略大
     */
    public static void method1() {
        sum = 0;
        long start = new Date().getTime();
        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            Thread tmp = new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        sum++;
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + sum);
                    latch.countDown();
                }
            }, "thread:" + i);
            tmp.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = new Date().getTime();
        System.out.println("method1 : " + start + "~" + end);
        System.out.println("method1 time: " + (end - start));
    }

    /**
     * 三个线程结果值：均比10000大，但都只是略大
     * 耗时更长
     */
    public static void method2() {
        sum1 = 0;
        long start = new Date().getTime();
        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 3; i < 6; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    sum1++;
                }

                System.out.println(Thread.currentThread().getName() + ": " + sum1);
                latch.countDown();
            }, "thread-" + i).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = new Date().getTime();
        System.out.println("method2 : " + start + "~" + end);
        System.out.println("method2 time: " + (end - start));
    }
}
