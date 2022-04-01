package org.example.com.niuke.huawei;

import java.util.Scanner;

//HJ29 字符串加解密
public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String a = sc.next();
            String b = sc.next();
            System.out.println(encode(a));
            System.out.println(decode(b));
        }
    }

    private static String encode(String s) {
        int n = s.length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int nxt = c - '0';
                if (nxt == 9) {
                    sb.append('0');
                } else {
                    sb.append(nxt + 1);
                }
            } else if (Character.isLetter(c)) {
                char nxt = (char) (c +1);
                if (c == 'z') {
                    sb.append('A');
                } else if (c =='Z') {
                    sb.append('a');
                } else if (c >= 'a' && c < 'z') {
                    sb.append(Character.toUpperCase(nxt));
                } else {
                    sb.append(Character.toLowerCase(nxt));
                }
            }
        }

        return sb.toString().toUpperCase();
    }

    private static String decode(String s) {
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int nxt = c - '0';
                if (nxt == 0) {
                    sb.append('9');
                } else {
                    sb.append(nxt - 1);
                }
            } else if (Character.isLetter(c)) {
                char nxt = (char) (c -1);
                if (c == 'a') {
                    sb.append('Z');
                } else if (c =='A') {
                    sb.append('z');
                } else if (c > 'a' && c <= 'z') {
                    sb.append(Character.toUpperCase(nxt));
                } else {
                    sb.append(Character.toLowerCase(nxt));
                }
            }
        }

        return sb.toString().toLowerCase();
    }
}
