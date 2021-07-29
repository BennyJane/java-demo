package com.benny.learning.algorithm.leetcode.dp.bag;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * https://leetcode-cn.com/problems/coin-change/
 */
public class Q2 {

    public int coinChange(int[] coins, int amount) {
        int m = coins.length;
        int INF = amount + 1;
        Arrays.sort(coins); // 必须排队
        int[][] dp = new int[m][amount + 1];
        // dp[i][0]的情况下取值都是0
        // 考虑第一个硬币的情况
        for (int i = 1; i <= amount; i++) {
            if (i >= coins[0] && i % coins[0] == 0) {
                dp[0][i] = i / coins[0];
            } else {
                dp[0][i] = INF;
            }
        }

        for (int i = 1; i < m; i++) {
            int c = coins[i];
            for (int j = 1; j <= amount; j++) {
                if (j >= c) {
                    int pre = dp[i][j - c];
                    if (pre != INF) {
                        dp[i][j] = pre + 1;
                        continue;
                    }
                }
                dp[i][j] = dp[i - 1][j];
            }
        }

        return dp[m - 1][amount] == amount + 1 ? -1 : dp[m - 1][amount];
    }

    public static void main(String[] args) {
        Q2 q = new Q2();
        int[] nums = new int[]{
//                1, 2, 5
//                2, 5, 10, 1
                456, 117, 5, 145
        };
        q.coinChange(nums, 1459);
    }
}


