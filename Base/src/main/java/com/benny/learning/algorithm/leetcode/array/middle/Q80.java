package com.benny.learning.algorithm.leetcode.array.middle;

import java.time.chrono.MinguoChronology;

/**
 * 775. 全局倒置与局部倒置
 * https://leetcode-cn.com/problems/global-and-local-inversions/
 */
public class Q80 {
    public boolean isIdealPermutation(int[] nums) {
        int len = nums.length;

        int min = len;
        for (int i = len - 2; i >= 0; i--) {
            int x = nums[i];
            int y = nums[i + 1];
            if (x > y) {
                min = Math.min(y, min);
            }
            if (x > min) {
                return false;
            }
        }
        return true;
    }
}


