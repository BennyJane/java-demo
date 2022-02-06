package org.example.com.theory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class VolatileDemo {
    public static void main(String[] args) {
//        TestDemo1();
//        TestDemo2();
        TestDemo3();
    }


    public static void TestDemo1() {
        AtomicReference<Boolean> condition = new AtomicReference<>(true);
        Thread thread1 = new Thread(() -> {
            while (condition.get()) {
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("run1 ...");
            }
            System.out.println("run1 end ...");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("run2 ...");
            try {
                Thread.sleep(1000L);
                condition.set(false);
                System.out.println(condition);
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }

    public static void TestDemo2() {
        AtomicInteger count = new AtomicInteger();
        Thread thread1 = new Thread(() -> {
            while (count.get() == 0) {
                System.out.println("run1-0 ..." + count.get());
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("run1 ..." + count[0]);
            }
            System.out.println("run1 end ...");
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
//            System.out.println("run2 ...");
            try {
                Thread.sleep(1000L);
                count.addAndGet(1);
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread2.start();
    }

    public static void TestDemo3() {
        Map<String, String> config = new HashMap<>();
        AtomicBoolean initialized = new AtomicBoolean(false);
        Thread thread1 = new Thread(() -> {
            config.put("name", "benny");
            System.out.println("run1 end ...");
            initialized.set(true);
        });

        Thread thread2 = new Thread(() -> {
            while (!initialized.get()) {
                try {
                    Thread.sleep(1L);
                    System.out.println("wait...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String name = config.get("name");
            System.out.println(name);
        });

        thread2.start();
        thread1.start();
    }

}
