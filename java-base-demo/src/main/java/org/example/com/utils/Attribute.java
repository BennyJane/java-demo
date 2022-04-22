package org.example.com.utils;

public class Attribute {
    private Attribute() {
    }

    /**
     * 获取CPU核心数，逻辑核心数
     *
     * @return
     */
    public static int getParallelism() {
        int parallelism = Runtime.getRuntime().availableProcessors();
        System.out.println(parallelism);
        return parallelism;
    }

    public static void getRuntime() {
        // JVM的总内存
        System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024);
        System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024);
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024);
    }


    public static void main(String[] args) {
        Attribute.getParallelism();
        Attribute.getRuntime();
    }
}
