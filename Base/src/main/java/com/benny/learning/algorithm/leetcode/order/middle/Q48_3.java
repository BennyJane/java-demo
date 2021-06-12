package com.benny.learning.algorithm.leetcode.order.middle;

/**
 * 55. 跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/
 */
public class Q48_3 {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int rightMost = 0;
        for (int i = 0; i < len; i++) {
            if (i <= rightMost) {
                rightMost = Math.max(rightMost, i + nums[i]);
                if (rightMost >= len - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Q48_3 q = new Q48_3();
//        int[] nums = {2, 0, 0};
//        int[] nums = {0, 0};
//        int[] nums = {2, 3, 1, 1, 4};
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(q.canJump(nums));
    }
}


