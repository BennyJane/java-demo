package org.example.com.theory;

// 实现死锁案例
public class DeadLockDemo extends Thread {
    // 锁的资源对象，实例
    private String first;
    private String second;

    public DeadLockDemo(String name, String first, String second) {
        // 父类的构造方法，给线程命名
        super(name);
        this.first = first;
        this.second = second;
    }

    @Override
    public void run() {
        System.out.println(this.getName() + "obtained: " + first);
        synchronized (first) {
            try {
                Thread.sleep(1000L);
                synchronized (second) {
                    System.out.println(this.getName() + "obtained: " + second);
                }
            } catch (InterruptedException e) {
                System.out.println("DeadLockDemo :" + e);
            }
        }
    }

}
