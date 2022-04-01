package org.example.com.niuke.huawei;

import java.util.Arrays;
import java.util.Scanner;

public class Q9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                int score = 0;
                int[] cnt = new int[26];
                // 此处必须使用.next() 方法，nextLine()方法报错
                String s = sc.next();
                s = s.toLowerCase();
                for (char c : s.toCharArray()) {
                    cnt[c - 'a']++;
                }

                Arrays.sort(cnt);
                int b = 26;
                for (int j = 25; j >= 0; j--) {
                    int c = cnt[j];
                    if (c > 0) {
                        score += c * b;
                        b--;
                    }
                }
                System.out.println(score);
            }
        }
    }
}
