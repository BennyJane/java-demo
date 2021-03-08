package com.benny.firstweb;

public class Utils {
    public static <T> void print(T s, String prefix) {
        System.out.println(prefix + " " + s);
    }

    public static <T> void print(T s) {
        System.out.println(s);
    }
}
