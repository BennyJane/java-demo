package com.benny.learning.algorithm.leetcode.tanxin;

/**
 * https://leetcode-cn.com/problems/smallest-range-i/
 * 908. 最小差值 I
 */
public class Q5 {
    public int smallestRangeI(int[] nums, int k) {
        int min = nums[0], max = nums[0];
        for (int x : nums) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        return Math.max(0, max - min - 2 * k);
    }
}


