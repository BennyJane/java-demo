package org.example.com.match.Xiapi;


//求最大连续子数组乘积，有正有负，复杂度要求 O(n)

/**
 * ## 参考题目：偶数个o的字符串长度
 * 遇到0，重新开始统计,即用0分割数组
 * 统计左侧到第一个负数前的乘积
 * 统计右侧到第一个负数前的乘积
 * 当负数数量为奇数时，考虑删除最左侧 或 最右侧 乘积
 */
public class Q1 {
    public static void main(String[] args) {
        int[] data = {};
        int result = GetSubArrayMaxProduct(data);
        System.out.println(result);
    }

    private static int GetSubArrayMaxProduct(int[] nums) {
        int ans = nums[0];
        if (nums.length == 1) return ans;
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            int current = nums[i];
            int max = Math.max(dp[i - 1][1] * current, dp[i - 1][0] * current);
            int min = Math.min(dp[i - 1][1] * current, dp[i - 1][0] * current);
            dp[i][0] = Math.max(current, max);
            dp[i][1] = Math.min(current, min);

            ans = Math.max(ans, dp[i][0]);
            ans = Math.max(ans, dp[i][1]);
        }

        return ans;
    }

    private static int GetSubArrayMaxProduct1(int[] nums) {
        int ans = nums[0];
        if (nums.length == 1) return ans;
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            int current = nums[i];
            if (current < 0) {
                dp[i][0] = Math.max(current, dp[i - 1][1] * current);
                dp[i][1] = Math.min(current, dp[i - 1][0] * current);
            }
            if (current >= 0) {
                dp[i][0] = Math.max(current, dp[i - 1][0] * current);
                dp[i][1] = Math.min(current, dp[i - 1][1] * current);
            }

            ans = Math.max(ans, dp[i][0]);
            ans = Math.max(ans, dp[i][1]);
        }

        return ans;
    }
}

class Solution {
    public int maxProduct(int[] nums) {
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; ++i) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }
}
