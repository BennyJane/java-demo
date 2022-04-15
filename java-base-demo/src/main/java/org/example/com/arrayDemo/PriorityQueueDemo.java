package org.example.com.arrayDemo;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://www.cnblogs.com/gnivor/p/4841191.html
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {

    }

    private static void method() {
        PriorityQueue<Integer> queue = new PriorityQueue();
        queue.add(10);
        queue.add(4);
        queue.add(5);
        queue.add(1024);
        queue.add(1);
        queue.add(0);

        System.out.println(queue.peek());

    }

    private static void method1() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        });

        PriorityQueue<String> queue1 = new PriorityQueue<>(
                (a, b) -> a.compareTo(b)
        );

        PriorityQueue<String> queue2 = new PriorityQueue<>(
                (a, b) -> {
                    if (a.length() == b.length()) {
                        return a.compareTo(b);
                    }
                    return a.length() - b.length();
                } // 尾部不需要分号
        );
    }
}
