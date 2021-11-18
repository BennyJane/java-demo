package org.exmaple.com.leetcode.dp.simple;

public class Q2 {
    // 部分条件不满足
    public int numDecodings(String s) {
        int len = s.length();
        if (s.charAt(0) == '0'
                || len == 0) {
            return 0;
        }
        // dp[i]: 长度为i的字符串，对应的解码数量
        int[] dp = new int[len + 1];
        dp[1] = 1;
        for (int i = 2; i <= len; i++) {
            int num = Integer.parseInt(s.substring(i - 2, i));
            // 连续两个0，直接返回0
            if (s.charAt(i - 1) == '0' && s.charAt(i) == '0') {
                return 0;
            }
            if (s.charAt(i - 1) == '0'
                    || s.charAt(i - 2) == '0') {
                dp[i] = dp[i - 1];
            } else if (i < len && s.charAt(i) == '0') {
                dp[i] = dp[i - 1];
            } else if (num > 26) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1] + (dp[i - 1] + 1) / 2;
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        Q2 q = new Q2();
//        q.numDecodings("10");
//        q.numDecodings("2101");
//        q.numDecodings("1123");
        q.numDecodings("10011");
    }
}


