package org.example.com.features.forkjoinpoll;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class Demo {

    private static void getStreamName() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }

        list.parallelStream().forEach(i -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + i);
        });

        list.stream().parallel().forEach(i -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + i);
        });


        list.stream().forEach(i -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + i);
        });

        IntStream.range(0, 15).parallel().forEach(i ->  {
            System.out.println(Thread.currentThread());
        });

    }

    private static void forkJoinDemo() {
        ForkJoinPool pool = new ForkJoinPool(3);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }

        pool.submit(() -> {
            list.parallelStream().forEach(e -> {
                System.out.println(Thread.currentThread().getName() + e);
            });
        }).join();

        System.out.println("forkJoinDemo is over.");
    }

    public static void main(String[] args) {
        getStreamName();
//        forkJoinDemo();
    }
}
