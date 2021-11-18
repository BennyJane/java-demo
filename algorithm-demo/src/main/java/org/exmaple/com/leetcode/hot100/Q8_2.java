package org.exmaple.com.leetcode.hot100;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * https://leetcode-cn.com/problems/coin-change/
 * <p>
 * 参考丑数；超级丑数类型题目
 */
public class Q8_2 {
    // 1: 统计硬币可以表示的金额 -》 可能会超时
    // 2: 直接判断amount是否可以用已有金币构成

    // 还是模拟法
    public int coinChange(int[] coins, int amount) {
        int len = coins.length;
        int[] f = new int[amount + 1];
        Arrays.fill(f, amount + 1);
        f[0] = 0;   // 必须修改0处的值
        // 2以下的函数返回值因为+1超过int的限制
        for (int i = 1; i <= amount; i++) {
            int min = amount + 1;
            for (int j = 0; j < len; j++) {
                if (coins[j] <= i) {
                    min = Math.min(min, f[i - coins[j]]);
                }
            }
            f[i] = min + 1;
        }
        return f[amount] > amount ? -1 : f[amount];
    }

    public int coinChange3(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // 参考完全平方数 https://leetcode-cn.com/problems/perfect-squares/
    // 没想明白
    public int coinChange2(int[] coins, int amount) {
        int len = coins.length;
        int[] f = new int[amount + 1];
        // TODO bug [2] 3的测试用例中，返回结果异常
        // 2以下的函数返回值因为+1超过int的限制
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < len; j++) {
                if (coins[j] <= i) {
                    min = Math.min(min, f[i - coins[j]]);
                }
            }
            f[i] = min + 1;
        }
        return f[amount] > amount ? -1 : f[amount];
    }

    public static void main(String[] args) {
        Q8_2 q = new Q8_2();
        int[] nums = new int[]{2};
        q.coinChange(nums, 3);
    }
}


