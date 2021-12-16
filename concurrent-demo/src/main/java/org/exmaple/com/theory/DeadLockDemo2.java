package org.exmaple.com.theory;

public class DeadLockDemo2 {

    public static Object object1 = new Object();
    public static Object object2 = new Object();


    public static void method1() {
        synchronized (object1) {
            try {
                System.out.println("thread1-1");
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            synchronized (object2) {
                System.out.println("thread1-2");
            }
        }
    }


    public static void method2() {
        synchronized (object2) {
            try {
                System.out.println("thread2-1");
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            synchronized (object1) {
                System.out.println("thread2-2");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(DeadLockDemo2::method1);
        Thread t2 = new Thread(DeadLockDemo2::method2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
