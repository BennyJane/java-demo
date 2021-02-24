package com.benny.firstweb.streamDemo;

import org.springframework.aop.ThrowsAdvice;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;

/**
 * @description 熟悉parallelStream的使用
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        streamDemo();
        streamTest();
    }


    public static void streamDemo() {
        // 异步执行
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
                .forEach(out -> System.out.print(out + ", "));
    }

    public static void streamTest() throws InterruptedException {
        // 构造一个10000个元素的集合
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        Set<Thread> threadSet = new CopyOnWriteArraySet<>();
        // 统计并执行list的线程
        list.parallelStream().forEach(integer -> {
            Thread thread = Thread.currentThread();
            // 统计并行执行list的线程
            threadSet.add(thread);
        });
        System.out.println("\n-------------------------------");
        System.out.println("threadSet一共有" + threadSet.size() + "个线程.");
        System.out.println("系统总共有" + Runtime.getRuntime().availableProcessors() + "个CPU");


        System.out.println("\n-------------------------------");
        // 第二个案例
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list1.add(i);
            list2.add(i);
        }

        Set<Thread> threadSet2 = new CopyOnWriteArraySet<>();
        CountDownLatch countDownLatch1 = new CountDownLatch(2);
        Thread threadA = new Thread(() -> {
            list1.parallelStream().forEach(integer -> {
                Thread thread = Thread.currentThread();
                threadSet2.add(thread);
            });
            countDownLatch1.countDown();
        });

        Thread threadB = new Thread(() ->{
           list2.parallelStream().forEach(integer ->{
               Thread thread = Thread.currentThread();
               threadSet2.add(thread);
           });
           countDownLatch1.countDown();
        });

        threadA.start();
        threadB.start();
        countDownLatch1.await();
        System.out.print("threadSetTwo一共有" + threadSet2.size() + "个线程");


        System.out.println("---------------------------");
        System.out.println(threadSet);
        System.out.println(threadSet2);
        System.out.println("---------------------------");
        threadSet2.addAll(threadSet);
        System.out.println(threadSet2);
        System.out.println("threadSetTwo一共有" + threadSet2.size() + "个线程");
        System.out.println("系统一个有"+Runtime.getRuntime().availableProcessors()+"个cpu");
    }

}
