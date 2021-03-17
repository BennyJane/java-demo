package com.benny.learning.java8Demo;

/**
 * 函数接口：只有一个函数的接口，该接口可以隐式的转化为Lambda表达式
 */
@FunctionalInterface
public interface FunctionInterface {
    void uniqueFunc();
    // 定义第二个方法，编译阶段就会报错
    //void secondFunc();

    // 可以定义默认方法; 实现类继承，但不需要实现
    default void notRequired() {
        System.out.println("FunctionInterface default method");
    }
    // 静态方法
    static void staticMethod() {
        System.out.println("static method");
    }
}
