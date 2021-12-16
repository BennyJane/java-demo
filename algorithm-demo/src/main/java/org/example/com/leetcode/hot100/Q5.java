package org.example.com.leetcode.hot100;

import javax.swing.*;

/**
 * 494. 目标和
 */
public class Q5 {

    private int ans;

    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return ans;
    }

    private void dfs(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                ans++;
            }
            return;
        }

        dfs(nums, target, index + 1, sum - nums[index]);
        dfs(nums, target, index + 1, sum + nums[index]);
    }
}


