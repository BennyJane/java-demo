package com.benny.firstweb.streamDemo;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @description java8使用并行流parallelStream以及普通迭代,并行流，普通流之间的效率对比
 */
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        streamDemo();
    }

    /**
     * https://blog.csdn.net/qq_20009015/article/details/84892083外部调用的ArrayList，HashMap等也必须使用和其对等的线程安全类，例如：ConcurrentHashMap等
     */
    public static void streamDemo() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();

        // 锁机制
        Lock lock = new ReentrantLock();

        IntStream.range(0, 10000).forEach(list1::add);
        IntStream.range(0, 10000).parallel().forEach(list2::add);
        IntStream.range(0, 10000).parallel().forEach(i -> {
            lock.lock();
            try {
                list3.add(i);
            } finally {
                lock.unlock();
            }
        });

        System.out.println("串行执行的大小：" + list1.size());
        System.out.println("并行执行的大小：" + list2.size());
        System.out.println("加锁并行执行的大小：" + list3.size());
    }

}
