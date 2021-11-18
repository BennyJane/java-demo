package org.exmaple.com.stringDemo;

import java.util.Arrays;

public class ComDemo {
    public static void main(String[] args) {
        splitTest();
    }

    static void splitTest() {
        String str = "benny？jane";
        String[] res;
        res = str.split("");
        System.out.println(Arrays.asList(res));
        res = str.split(" ");
        System.out.println(Arrays.asList(res));
        res = str.split("e");
        System.out.println(Arrays.asList(res));
        res = str.split("e|a");
        System.out.println(Arrays.asList(res));
        res = str.split("n|\\？");
        System.out.println(Arrays.asList(res));
    }
}
