package com.benny.learning;

public class Utils {
    public static <T> void print(T s, String prefix) {
        System.out.println(prefix + " " + s);
    }

    public static <T> void print(T s) {
        System.out.println(s);
    }
}
