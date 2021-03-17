package com.benny.learning.java8Demo;

import java.util.Arrays;

public class Demo {
    static String globalVal = "Demo.value";

    public static void main(String[] args) {
        System.out.println("-------------------------------[lambda]");
        lambdaDemo();

    }

    /**
     * lambda 表达式（闭包）
     */
    static void lambdaDemo() {
        // -> 符号
        Arrays.asList("a", "b", "c").forEach(e -> System.out.println(e));
        // 显示指定参数类型（默认由编译器推算）
        Arrays.asList("a", "b", "c").forEach((String e) -> System.out.println(e));
        // 多行代码
        Arrays.asList("a", "b", "c").forEach((String e) -> {
            System.out.println(e);
            System.out.println(e + " 1");
        });

        // 引用局部变量以及外部变量(类静态变量|类成员变量)，这些变量会隐式得转化为final
        String localVal = "lambdaDemo.value";   // 等效 final String localVal
        Arrays.asList("a", "b", "c").forEach((String e) -> {
            System.out.println(e + " " + globalVal);
            System.out.println(e + " " + localVal);
        });

        // 返回值类型也无需指定
        // 代码块只有一行，无需使用return
        Arrays.asList("a", "b", "c").sort((m, n) -> m.compareTo(n));
        Arrays.asList("a", "b", "c").sort((m, n) -> {
            int res = m.compareTo(n);
            return res;
        });


    }
}
