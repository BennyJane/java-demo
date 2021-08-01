package com.benny.learning.algorithm.leetcode.dp.bag;

/**
 * 518. 零钱兑换 II
 * https://leetcode-cn.com/problems/coin-change-2/
 */
public class Q3 {
    public int change(int amount, int[] coins) {
        int len = coins.length;
        // dp[i][j]: 表示使用i个硬币组成和为j的组合数量
        int[][] dp = new int[len][amount + 1];
        for (int j = 1; j <= amount; j++) {
            int coin = coins[0];
            if (j % coin == 0) {
                dp[0][j] = 1;
            }
        }
        // dp[0][0]: 初始状态为1
        for (int j = 0; j < len; j++) {
            dp[j][0] = 1;
        }

        for (int i = 1; i < len; i++) {
            int coin = coins[i];
            for (int j = 1; j <= amount; j++) {
                if (j >= coin) {
                    dp[i][j] = dp[i][j - coin] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[len - 1][amount];
    }

    // 完全背包：朴素解法
    public int change2(int cnt, int[] cs) {
        int n = cs.length;
        // 定义  为考虑前  件物品，凑成总和为  的方案数量。
        int[][] f = new int[n + 1][cnt + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int val = cs[i - 1];
            for (int j = 0; j <= cnt; j++) {
                f[i][j] = f[i - 1][j];
                // 考虑当前硬币多次使用的情况
                for (int k = 1; k * val <= j; k++) {
                    f[i][j] += f[i - 1][j - k * val];
                }
            }
        }
        return f[n][cnt];
    }

    // 优化： 换元一维优化
    //    在二维解法的基础上，直接取消「物品维度」
    //    确保「容量维度」的遍历顺序为「从小到大」（适用于「完全背包」）
    //    将形如  的式子更替为 ，同时解决「数组越界」问题（将物品维度的遍历修改为从  开始）
    public int change3(int cnt, int[] cs) {
        int n = cs.length;
        int[] f = new int[cnt + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            int val = cs[i - 1];
            for (int j = val; j <= cnt; j++) {
                f[j] += f[j - val];
            }
        }
        return f[cnt];
    }


    public static void main(String[] args) {
        Q3 q = new Q3();
        int[] nums = new int[]{1, 2, 5};
        q.change(5, nums);
    }
}


