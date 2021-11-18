package org.exmaple.com.baseDemo;

public class StringDemo {
    public static void main(String[] args) {
        funcExc();
        System.out.println("========  == equals =============");
        funcExc2();
        System.out.println("========  ASCII =============");
        funcExc3();

    }

    static void funcExc() { // ASCII值：数字 < 大写字母 < 小写字母(-32)
        int ab = 'a' - 'b';
        System.out.println("字符串a的b差值： " + ab);
        int Aa = 'A' - 'a';
        System.out.println("字符串A的a差值： " + Aa);


        int res1 = '1' - 'A';
        System.out.println("字符串1的A差值： " + res1);

        int res2 = '1' - '0';
        System.out.println("字符串1的0差值： " + res2);

        int res3 = 1 - '0';
        System.out.println("字符串A的a差值： " + res3);
        String numStr = "0111";
        System.out.println("contains: ： " + numStr.contains("1"));
    }

    static void funcExc2() {
        String s1 = "benny";
        String s2 = new String("benny");
        if (s1 == s2) {
            System.out.println("==: s1 s2 相等.");
        }

        if (s1.equals(s2)) {
            System.out.println("equals: s1 s2 相等.");
        }
    }

    static void funcExc3() {
        // https://blog.csdn.net/daimadog/article/details/89069635
        // 获取字符串ASCII值
        // Integer.valueOf(a) 只能接收char类型的对象，String类型会报错
        // String[] l = {"0", "9", "A", "Z", "a", "z"};
        char[] l = {'0', '9', 'A', 'Z', 'a', 'z'};
        for (char item : l) {
            int s = Integer.valueOf(item);
            System.out.println(item + " " + s);
        }

        char a='a';
        char A='A';
        int a_ascii=Integer.valueOf(a);
        int A_ascii=Integer.valueOf(A);
        System.out.println("字符"+a+"的ASCII码为："+a_ascii);
        System.out.println("字符"+A+"的ASCII码为："+A_ascii);
        System.out.println(A-2);
        System.out.println(A_ascii-a_ascii);
    }
}
