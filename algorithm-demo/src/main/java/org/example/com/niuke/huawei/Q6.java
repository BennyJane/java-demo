package org.example.com.niuke.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//HJ33 整数与IP地址间的转换
public class Q6 {

    /**
     * 进制转换 + int 越界问题
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String ip = sc.nextLine();
            if (ip.contains(".")) {
                // TODO 此处必须使用\\转义符号
                String[] nums = ip.split("\\.");
                StringBuilder sb = new StringBuilder();
                for (String s : nums) {
                    String b = tenToBinary(s);
                    sb.append(b);
                }
                long ans = toTen(sb.toString());
                System.out.println(ans);
            } else {
                String binaryStr = tenToBinary(ip);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    long tmp = toTen(binaryStr.substring(i * 8, (i + 1) * 8));
                    sb.append(tmp);
                    if (i != 3) {
                        sb.append(".");
                    }
                }
                System.out.println(sb.toString());
            }

        }
    }

    private static String tenToBinary(String oriStr) {
        long ori = Long.parseLong(oriStr);
        List<Long> list = new ArrayList<>();

        while (ori > 0) {
            long tmp = ori % 2;
            list.add(tmp);
            ori /= 2;
        }
        int n = list.size();

        int o = n % 8;
        if (o != 0 || n == 0) {
            o = 8 - o;
        }
        for (int i = 0; i < o; i++) {
            list.add(0L);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    private static long toTen(String s) {
        char[] arr = s.toCharArray();
        int n = s.length();
        int start = n - 1;
        long ans = 0;
        for (char c : arr) {
            int curNum = c - '0';
            ans += curNum * (Math.pow(2, start));
            start--;
        }
        return ans;
    }
}
