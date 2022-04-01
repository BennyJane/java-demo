package org.example.com.niuke.huawei;

import java.util.Scanner;

//59 找出字符串中第一个只出现一次的字符
public class Q12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.next();
            boolean flag = true;
            for (char c : s.toCharArray()) {
                if (s.indexOf(c) == s.lastIndexOf(c)) {
                    System.out.println(c);
                    flag = false;
                    break;
                }
            }

            if (flag) {
                System.out.println("-1");
            }
        }
    }
}
