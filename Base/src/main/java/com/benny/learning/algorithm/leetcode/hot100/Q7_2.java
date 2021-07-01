package com.benny.learning.algorithm.leetcode.hot100;

/**
 * 1277. 统计全为 1 的正方形子矩阵
 * https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/
 * <p>
 * 动态规划
 */
public class Q7_2 {
    public int countSquares(int[][] matrix) {
        if (matrix[0].length == 0) {
            return 0;
        }

        int count = 0;

        int m = matrix.length;
        int n = matrix[0].length;
        // dp 表示 (row, col)位置处可构成正方形的最大边长
        int[][] dp = new int[m][n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int cur = matrix[row][col];
                if (cur == 0) {
                    continue;
                }
                if (row == 0 || col == 0) {
                    dp[row][col] = 1;
                } else {
                    dp[row][col] = Math.min(
                            Math.min(dp[row - 1][col], dp[row][col-1]),
                            dp[row - 1][col - 1])
                            + 1;
                }
                count += dp[row][col];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Q7_2 q = new Q7_2();
        int[][] nums = new int[][] {
                {0,1,1,1},
                {1,1,1,1},
                {0,1,1,1},
        };
        q.countSquares(nums);
    }
}


