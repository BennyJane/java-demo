package org.example.com.leetcode.dp.simple;

public class Q2_1 {
    // 部分条件不满足
    public int numDecodings(String s) {
        int len = s.length();
        if (s.charAt(0) == '0'
                || len == 0) {
            return 0;
        }
        // dp[i]: 长度为i的字符串，对应的解码数量
        // 应该分为两种情况的和
        int[] dp = new int[len + 1];
        dp[0] = 1;  // TODO 空字符串 可以看作解码为空字符串的一种情况
        for (int i = 1; i <= len; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1]; // 最后一个解码为单字符
            }
            if (i > 1 && s.charAt(i - 2) != '0') {
                int num = Integer.parseInt(s.substring(i - 2, i));
                if (num <= 26) {
                    dp[i] += dp[i - 2];
                }
            }

        }
        return dp[len];
    }

    public static void main(String[] args) {
        Q2_1 q = new Q2_1();
//        q.numDecodings("10");
//        q.numDecodings("2101");
//        q.numDecodings("1123");
        q.numDecodings("10011");
    }
}


