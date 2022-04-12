package org.example.com.arrayDemo;

import java.util.PriorityQueue;

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
}
