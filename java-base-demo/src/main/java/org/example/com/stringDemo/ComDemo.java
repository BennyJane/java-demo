package org.example.com.stringDemo;

import java.util.Arrays;

public class ComDemo {
    public static void main(String[] args) {
        splitTest();
        IntegerTest();
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

//        boolean flag = str.contains("jane");
//        System.out.println(flag);


        // String.join(seq, array)
        System.out.println(String.join("--", res));
        System.out.println(String.join(" ", res));
        // 删除两侧空格
        System.out.println(" benny .     ".trim() + "||||");


        System.out.println(str.startsWith("be"));


        // 正则匹配
        boolean rs = str.matches(".*");
        System.out.println(rs);
    }

    public static void IntegerTest() {
        System.out.println("---------------------------------------");
        System.out.println(Integer.parseInt("10"));
        System.out.println(Integer.parseInt("10", 16));
        System.out.println(Integer.parseInt("10", 8));
        System.out.println(Integer.parseInt("10", 2));
        System.out.println(Integer.parseInt("AA", 16));
        // 0xAA 必须删除前面两个标识字符 0x
        String str = "AA";
        System.out.println(Integer.parseInt(str,16));
    }
}
