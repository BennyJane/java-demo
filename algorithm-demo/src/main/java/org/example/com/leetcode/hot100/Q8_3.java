package org.example.com.leetcode.hot100;


import java.util.Arrays;

/**
 * 518. 零钱兑换 II
 * https://leetcode-cn.com/problems/coin-change-2/
 * <p>
 * 参考丑数；超级丑数类型题目
 */
public class Q8_3 {

    private int count;
    // FIXME：模拟法 超时
    public int change(int amount, int[] coins) {
        Arrays.sort(coins);
        int len = coins.length;
        dfs(coins, len -1, amount);
        return count;
    }

    private void dfs(int[] coins, int index, int res) {
        if (index < 0) {
            if (res == 0) {
                count++;
            }
            return;
        }
        int coin = coins[index];
        int maxCount = res / coin;
        for (int i = 0; i <= maxCount; i++) {
            dfs(coins, index - 1, res - coin * i);
        }
    }



    public static void main(String[] args) {
        Q8_3 q = new Q8_3();
        int[] nums = new int[]{3, 5, 7, 8, 9, 10, 11};
        q.change(500, nums);
    }

}


