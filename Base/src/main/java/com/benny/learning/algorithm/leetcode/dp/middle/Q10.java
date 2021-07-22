package com.benny.learning.algorithm.leetcode.dp.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * https://leetcode-cn.com/problems/triangle/
 */
public class Q10 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int[][] dp = new int[2][m];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 0; i < m - 1; i++) {
            int curIndex = i & 1;
            int nextIndex = 1 - curIndex;
            // 第一列只能来自上方
            dp[nextIndex][0] = dp[curIndex][0] + triangle.get(i + 1).get(0);
            for (int j = 1; j < i + 1; j++) {
                dp[nextIndex][j] = Math.min(dp[curIndex][j - 1], dp[curIndex][j]) + triangle.get(i+1).get(j);
            }
            dp[nextIndex][i+1] = dp[curIndex][i] + triangle.get(i + 1).get(i + 1);
        }
        int finalIndex = (m - 1) & 1;
        int ans = dp[finalIndex][0];
        for (int i = 0; i < m; i++) {
            ans = Math.min(ans, dp[finalIndex][i]);
        }
        return ans;
    }
}


