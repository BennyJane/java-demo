package org.example.com.leetcode.dp.simple;

/**
 * 97. 交错字符串
 * https://leetcode-cn.com/problems/interleaving-string/
 */
public class Q1 {
    // 二维动态规划
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int t = s3.length();
        if (n + m != t) {
            return false;
        }
        // dp[x][y]:
        // 表示s1的前x个元素 和 s2的前y个元素，是否可以构成s3的前x+y个元素
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                int tailPos = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j]
                            || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(tailPos));
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j]
                            || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(tailPos));
                }
            }
        }
        return dp[m][n];
    }

    // 参考答案
    // https://leetcode-cn.com/problems/interleaving-string/solution/shou-hua-tu-jie-dfshui-su-dfsji-yi-hua-by-hyj8/

}


