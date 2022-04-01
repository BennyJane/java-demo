package org.example.com.niuke.huawei;

import java.util.Scanner;


/**
 * HJ63 DNA序列
 * 固定窗口宽度： 滑动窗口
 */
public class Q13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.next();
            int n = sc.nextInt();
            if (s.length() < n) {
                System.out.println(0);
                continue;
            }
            int left = 0, right = n - 1;
            int MaxCount = 0;
            int count = 0;
            String ans = s.substring(0, n);
            for (int i = 0; i <= right; i++) {
                if (s.charAt(i) == 'G' || s.charAt(i) == 'C') {
                    count++;
                }
            }
            MaxCount = count;
            while (right + 1 < s.length()) {
                if (s.charAt(left) == 'G' || s.charAt(left) == 'C') {
                    count--;
                }
                left++;

                right++;
                if (s.charAt(right) == 'G' || s.charAt(right) == 'C') {
                    count++;
                }
                if (count > MaxCount) {
                    ans = s.substring(left, right + 1);
                    MaxCount = count;
                }
            }
            System.out.println(ans);
        }
    }
}


class Main01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            int n = sc.nextInt();
            String result = "";
            int maxCount = 0;
            for (int i = 0; i <= str.length() - n; i++) {
                String tmp = str.substring(i, i + n);
//                String res = aim.replaceAll("[^CG]","");
                // FIXME 直接将关键单词替换为空
                String res = tmp.replaceAll("A|T", "");
                if (res.length() > maxCount) {
                    maxCount = res.length();
                    result = tmp;
                }
            }
            System.out.println(result);
        }
    }
}
