package com.benny.learning.algorithm.leetcode.order.middle;


/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class Q39 {
    // 有序数组查询，优先使用二分法
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int val = nums[mid];
            if (val == target) {
                left = mid;
                right = mid;
                while (left >= 0 && nums[left] == target) {
                    left--;
                }
                while (right < len && nums[right] == target) {
                    right++;
                }
                return new int[]{left + 1, right - 1};
            } else if (val > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return new int[]{-1, -1};
    }
}


