package org.example.com.niuke.huawei;

import java.util.Scanner;

public class Q6_1 {
    private final int N = 4;

    public String convert(String str) {
        // ipv4 -> int
        if (str.contains(".")) {
            String[] fields = str.split("\\.");
            long result = 0;
            for (int i = 0; i < N; i++) {
                result = result * 256 + Integer.parseInt(fields[i]);
            }
            return "" + result;
        }
        // int -> ipv4
        else {
            long ipv4 = Long.parseLong(str);
            String result = "";
            for (int i = 0; i < N; i++) {
                result = ipv4 % 256 + "." + result;
                ipv4 /= 256;
            }
            return result.substring(0, result.length() - 1);
        }
    }


}

class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.next();
            if (s.contains(".")) {
                System.out.println(ip2num(s));
            } else {
                System.out.println(num2ip(Long.parseLong(s)));
            }
        }
    }

    public static long ip2num(String ip) {
        String[] iip = ip.split("\\.");
        Long ans = (long) 0;
        for (int i = 0; i < 4; i++) {
            ans = ans * 256 + Long.parseLong(iip[i]);
        }
        return ans;
    }

    public static String num2ip(long num) {
        String[] ans = new String[4];
        for (int i = 3; i >= 0; i--) {
            ans[i] = Long.toString(num % 256);
            num = num / 256;
        }
        return String.join(".", ans);
    }
}


class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.next();
            if (s.contains(".")) {
                System.out.println(ip2num(s));
            } else {
                System.out.println(num2ip(Long.parseLong(s)));
            }
        }
    }

    public static long ip2num(String ip) {
        String[] iip = ip.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int num = Integer.parseInt(iip[i]);  // 拆分
            // toBinaryString()
            String num2 = Integer.toBinaryString(num);  //转换为二进制
            while (num2.length() < 8) {
                num2 = "0" + num2;  // 拼接
            }
            sb.append(num2);
        }
        return Long.parseLong(sb.toString(), 2);  // 转化为10进制
    }

    public static String num2ip(long num) {
        String num2 = Long.toBinaryString(num);  //转换为2进制
        while (num2.length() < 32) {
            num2 = "0" + num2;
        }
        String[] ans = new String[4];
        for (int i = 0; i < 4; i++) {
            String s = num2.substring(8 * i, 8 * i + 8);  //拆分
            s = Integer.toString(Integer.parseInt(s, 2));  //转化为10进制
            ans[i] = s;
        }
        // String.join()方法
        return String.join(".", ans);  //拼接
    }
}
