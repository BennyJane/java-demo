package org.exmaple.com.leetcode.hot100;

/**
 * 518. 零钱兑换 II
 * https://leetcode-cn.com/problems/coin-change-2/
 * <p>
 * 参考丑数；超级丑数类型题目
 */
public class Q8_3_1 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }


    public static void main(String[] args) {
        Q8_3_1 q = new Q8_3_1();
        int[] nums = new int[]{3, 5, 7, 8, 9, 10, 11};
        q.change(500, nums);
    }

}


