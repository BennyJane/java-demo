package com.benny.learning.algorithm.match.N259;

public class Q2 {
    public int sumOfBeauties(int[] nums) {
        int len = nums.length;
        int[] leftMax = new int[len];
        int max1 = Integer.MIN_VALUE;
        for (int i = 0; i < len - 1; i++) {
            max1 = Math.max(max1, nums[i]);
            leftMax[i + 1] = max1;
        }

        int[] rightMax = new int[len];
        int min1 = Integer.MAX_VALUE;
        for (int i = len - 1; i > 0; i--) {
            min1 = Math.min(min1, nums[i]);
            rightMax[i - 1] = min1;
        }

        int ans = 0;
        for (int i = 1; i < len; i++) {
            int cur = nums[i];
            if (cur > leftMax[i] && cur < rightMax[i]) {
                ans += 2;
            } else if (cur > nums[i - 1] && cur < nums[i + 1]) {
                ans++;
            } else {
                ans += 0;
            }
        }

        return ans;
    }
}
