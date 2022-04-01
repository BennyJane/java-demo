package org.example.com.niuke.huawei;

import java.util.Scanner;

//HJ57 高精度整数加法
public class Q11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String first = sc.nextLine();
            String second = sc.nextLine();
            int r1 = first.length() - 1, r2 = second.length() - 1;

            String ans = "";
            int add = 0;
            while (r1 >= 0 && r2 >= 0) {
                int a = first.charAt(r1) - '0';
                int b = second.charAt(r2) - '0';
                int tmp = a + b + add;
                if (tmp > 9) {
                    add = 1;
                    tmp %= 10;
                } else {
                    add = 0;
                }
                ans = tmp + ans;

                r1--;
                r2--;
            }

            while (r1 >= 0) {
                int tmp = first.charAt(r1) - '0';
                tmp += add;
                if (tmp > 9) {
                    add = 1;
                    tmp %= 10;
                } else {
                    add = 0;
                }
                ans = tmp + ans;
                r1--;
            }
            while (r2 >= 0) {
                int tmp = second.charAt(r2) - '0';
                tmp += add;
                if (tmp > 9) {
                    add = 1;
                    tmp %= 10;
                } else {
                    add = 0;
                }
                ans = tmp + ans;
                r2--;
            }
            // FIXME 末尾一定要处理 最后的进位
            if (add > 0) {
                ans = add + ans;
            }
            System.out.println(ans);
        }
    }
}
