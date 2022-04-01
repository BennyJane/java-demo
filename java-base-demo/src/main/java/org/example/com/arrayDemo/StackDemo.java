package org.example.com.arrayDemo;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class StackDemo {
    public static void main(String[] args) {
        method1();
    }
    // LinkedList ArrayDeque 都可以实现双端队列
    public static void method1() {
         Deque<String> dequeByLinkedList = new LinkedList<>();
        Deque<String> deque = new ArrayDeque();
        deque.push("01");   // addFirst 添加到首部
        deque.add("02");    // addLast 添加到末尾
        deque.addFirst("03");
        deque.addLast("05");
        deque.offer("100"); // offerLast 在末尾添加元素
        deque.offerLast("101"); // addLast
        deque.offerFirst("102"); // addFirst
        String[] arr = {"06", "07"};
        deque.addAll(Arrays.asList(arr));
        System.out.println(deque);
        // 不影响deque内部值
        arr[0] = "10";
        System.out.println(deque);

        int size = deque.size();
        System.out.println("size: " + size);
        String topVal = deque.pop(); // removeFirst 获取首部值
        System.out.println(topVal);

        String pollVal = deque.poll();   // pollFirst 获取首部元素
        System.out.println(pollVal);
        String pollLastVal = deque.pollLast();   // 弹出末尾元素
        System.out.println(pollLastVal);
        String pollFirstVal = deque.pollFirst();  // 弹出首部元素
        System.out.println(pollFirstVal);
    }


    public static void method2() {
        System.out.println("[method2] =================================================");
        Deque<String> deque = new LinkedList<>();
    }
}
