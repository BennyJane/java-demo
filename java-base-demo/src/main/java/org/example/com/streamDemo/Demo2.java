package org.example.com.streamDemo;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @description 并行的BUG
 */
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        streamDemo();
    }

    /**
     * 在使用parallelStream时，需要注意线程安全的问题，该加锁的就加锁，
     * 外部调用的ArrayList，HashMap等也必须使用和其对等的线程安全类，例如：ConcurrentHashMap等
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
