package com.benny.firstweb.aopDemo.cglib;

public class SayHello {
    public void say() {
        System.out.println("hello world!");
    }

    public static void main(String[] args) {
        SayHello s = new SayHello();
        s.say();
    }
}
