package org.example.com.volatileDemo;

public class ThreadTest implements Runnable {

    public static volatile int sum = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {

        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            sum++;
        }

        System.out.println(Thread.currentThread().getName() + ": " + sum);

    }
}
