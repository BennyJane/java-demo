package com.example.demo.Learn;

public class Demo2 {
    public static void main(String[] args) {
        // getClass()
        Demo1 d = new Demo1("wuhan");
        System.out.println("getClass() : " + d.getClass());

        // class
        Class<?> cls = Demo1.class;
        System.out.println("Class.class : " + cls);
        System.out.println(".getName : " + cls.getName());
        System.out.println(".getCanonicalName : " + cls.getCanonicalName());
        System.out.println(".getCanonicalName : " + cls.getCanonicalName());
    }
}
