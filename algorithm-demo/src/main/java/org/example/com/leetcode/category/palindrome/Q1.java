package org.example.com.leetcode.category.palindrome;

//https://leetcode-cn.com/problems/longest-palindromic-substring/
public class Q1 {
    public String longestPalindrome(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        int maxlen = 1;
        String ans = s.substring(0, 1);
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    //  确保内部区间上也是回文
                    if (j - 1 > i + 1 && dp[i + 1][j - 1] != (j - 2 - i + 1)) {
                        continue;
                    }
                    // 只处理[i+1, j-1]是回文的情况
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }

                // 不处理两个端点不等的情况 ==》 必然不是回文字符串

                if (dp[i][j] > maxlen) {
                    ans = s.substring(i, j + 1);
                    maxlen = dp[i][j];
                }
            }
        }
        return ans;
    }

}
