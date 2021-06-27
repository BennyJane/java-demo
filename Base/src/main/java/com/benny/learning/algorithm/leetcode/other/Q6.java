package com.benny.learning.algorithm.leetcode.other;

// 分苹果
public class Q6 {

    public static void main(String[] args) {
        int m = 7;
        int n = 3;
        System.out.println("情况总数：" + distribute_apple(m, n));
    }

    static int distribute_apple(int m, int n) {
        if (m == 0 || n == 1) {
            return 1;
        }
        if (m < n) {
            return distribute_apple(m, m);
        } else {
            return distribute_apple(m - n, n) + distribute_apple(m, n - 1);
        }
    }

}
