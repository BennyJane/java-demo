package org.exmaple.com.leetcode.order.middle;

import com.benny.learning.algorithm.Q;

/**
 * 33. 搜索旋转排序数组
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 */
public class Q43 {
    public int search(int[] nums, int target) {
        int len = nums.length;

        return midSearch(nums, 0, len - 1, target);
    }

    public int midSearch(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        while (left <= right) {
            int mid = (left + right) / 2;
            int val = nums[mid];
            if (val == target) {
                return mid;
            }

            if (nums[0] <= val) {   // mid 左侧为递增序列
                if (target >= nums[left] && target < val) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {   // mid 右侧为递增序列
                if (target <= nums[right] && target > val) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Q43 q = new Q43();
//        int[] nums = {4,5,6,7,0,1,2};
//        int res = q.search(nums, 0);
        int[] nums = {1,3};
        int res = q.search(nums, 3);
        System.out.println(res);
    }
}


