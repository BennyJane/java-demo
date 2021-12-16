package org.example.com.theory;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadManageDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();

        Runnable deadCheck = new Runnable() {
            @Override
            public void run() {
                // 查询死锁情况
                long[] threadIds = mxBean.findDeadlockedThreads();
                if (threadIds != null) {
                    ThreadInfo[] threadInfos = mxBean.getThreadInfo(threadIds);
                    System.out.println("Detected deadlock threads");
                    for (ThreadInfo threadInfo : threadInfos) {
                        System.out.println(threadInfo.getThreadName());
                    }
                } else {
                    System.out.println("there is no dead thread...");
                }
            }
        };

        // 构造死锁
        deadLock();

        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
        // 等待5秒后，每10秒进行一次死锁扫描
        schedule.scheduleAtFixedRate(deadCheck, 5L, 10L, TimeUnit.SECONDS);
    }

    public static void deadLock() throws InterruptedException {
        String lockA = "lockA";
        String lockB = "lockB";

        DeadLockDemo t1 = new DeadLockDemo("Thread1", lockA, lockB);
        DeadLockDemo t2 = new DeadLockDemo("Thread2", lockB, lockA);
        t1.start();
        t2.start();
        // 等待线程结束，因为死锁，所以为永久的阻塞在t1.join()
//        t1.join();
//        t2.join();
    }
}
