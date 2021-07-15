package com.benny.learning.algorithm.leetcode.dp.middle;

/**
 * 918. 环形子数组的最大和
 * https://leetcode-cn.com/problems/maximum-sum-circular-subarray/
 * <p>
 * 较为经典：可以总结
 */
public class Q5 {
    // 子数组连续
    public int maxSubarraySumCircular(int[] nums) {
        int len = nums.length;

        int left = 0;
        int ans = Integer.MIN_VALUE;
        while (left < len) {
            int sum = nums[left];
            int max = nums[left];
            int right = left + 1;
            while (right <= left + len - 1
                    && sum + nums[right % len] > 0) {
                sum += nums[right % len];
                max = Math.max(sum, max);
                right++;
            }
            ans = Math.max(ans, max);
            left++;
        }
        return ans;
    }

    // 超出内存限制
    public int maxSubarraySumCircular2(int[] nums) {
        int len = nums.length;
        // 前缀和
        int[] pre = new int[len + 1];
        for (int i = 0; i < len; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        int sum = pre[len] - pre[0];
        //
        int ans = Integer.MIN_VALUE;
        int[][] dp = new int[len + 1][len + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                if (i <= j) {
                    dp[i][j] = Math.max(nums[i - 1], pre[j] - pre[i - 1]);
                } else {
                    // 计算反向部分的和
                    dp[i][j] = Math.max(nums[i - 1], sum - (pre[i - 1] - pre[j]));
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Q5 q = new Q5();
        int[] nums = new int[]{
//                1, -2, 3, -2
//                3, -1, 2, -1
                5, -3, 5
        };
//        q.maxSubarraySumCircular(nums);
        q.maxSubarraySumCircular2(nums);
    }
}


