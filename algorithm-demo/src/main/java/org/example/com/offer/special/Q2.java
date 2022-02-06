package org.example.com.offer.special;

import java.util.Arrays;

//剑指 Offer II 102. 加减的目标值
public class Q2 {
    // 回溯法
    int count = 0;

    public int findTargetSumWays2(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }

    // 目标和
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 假设全部负数和为-neg，则剩余正数和为 sum - neg
        // target = sum - neg - neg
        // neg = (sum - target) / 2
        int diff = sum - target;
        // target 需要小于sum; 且 sum - target 必须为非负偶数
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }

        int n = nums.length;
        int neg = diff / 2;

        // dp[i][j]： 前i个元素中和为j的方案数
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;

        for (int i = 1; i < n + 1; i++) {
            int cur = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                // 初始化：不考虑当前值，和为j的方案数
                dp[i][j] = dp[i - 1][j];
                if (j >= cur) {
                    dp[i][j] += dp[i - 1][j - cur];
                }
            }
        }
        return dp[n][neg];
    }

    // 使用滚动数组优化
    public int findTargetSumWays3(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();

        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }

        int neg = diff / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;

        for (int cur : nums) {
            // FIXME 此处必须取到0； 必须从后往前遍历
            for (int i = neg; i >= 0; i--) {
                if (i >= cur) dp[i] += dp[i - cur];
            }
        }
        return dp[neg];
    }
}
