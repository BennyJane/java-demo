package org.example.com.theory;

import java.util.concurrent.ConcurrentHashMap;

public class Demo01 {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("run....");
            }
        };
        Thread thread = new Thread(runnable);

        Demo01 d = new Demo01();

        d.isHoldsLock();
    }

    public void isHoldsLock() {
        Integer num = 0;
        synchronized (this) {
            boolean res1 = Thread.holdsLock(this);
            System.out.println("1. " + res1);
            System.out.println(num);
            num += 1;
        }
        // 不能直接使用变量res1
        // System.out.println(res1);
        // synchronized 内的作用域与外部不同，可以出现同名变量
        boolean res1 = true;


        boolean res2 = Thread.holdsLock(this);
        System.out.println("2. " + res2);
        System.out.println("3. " + num);
    }


    public void method1() {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();

    }

}
