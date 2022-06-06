package org.example.com.tryCatchDemo;

public class TryDemo {
    public static void main(String[] args) {
        System.out.println(method0());
        // finally中使用return返回
        System.out.println(method1());
    }

    public static int method0() {
        int result = 0;

        try {
            System.out.println(result);
            return result;
        } finally {
            result = 1;
            System.out.println("finally exec: " + result);
        }
    }

    public static int method1() {
        int result = 0;

        try {
            System.out.println(result);
            return result;  // 被finally中return语句覆盖
        } finally {
            result = 1;
            System.out.println("finally exec: " + result);
            return result;
        }
    }
}
