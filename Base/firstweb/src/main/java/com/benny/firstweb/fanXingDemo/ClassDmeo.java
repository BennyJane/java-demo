package com.benny.firstweb.fanXingDemo;

public class ClassDmeo<T> {
    private T value;

    public ClassDmeo(T value) { // 构造器
        this.value = value;
    }

    public void setDemo(T value) {  // 入参类型
        this.value = value;
    }

    public T getDemo() {    // 返回值类型
        return this.value;
    }
}
