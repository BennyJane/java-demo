package org.example.com.features.volatile_;

/**
 * 执行结果可能为 (1,0) (0,1) (1,1) (0,0)
 */
public class Demo2 {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        method();
    }

    public static void method() throws InterruptedException {
        for (int i = 0; i < 100000; i++) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            Thread one = new Thread(new Runnable() {
                public void run() {
//                    try {
//                        Thread.sleep(new Random().nextInt(10));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    a = 1;
                    x = b;
                }
            });

            Thread other = new Thread(new Runnable() {
                public void run() {
//                    try {
//                        Thread.sleep(new Random().nextInt(1));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    b = 1;
                    y = a;
                }
            });
            one.start();
            other.start();
            one.join();
            other.join();
//            System.out.println("(" + x + ", " + y + ")");
            if (x == y) {
                System.out.println("==============================" + x);
            }
        }
    }
}
