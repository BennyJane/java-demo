package org.exmaple.com.leetcode.array.simple;

/**
 * 35. 搜索插入位置
 * https://leetcode-cn.com/problems/search-insert-position/
 *
 * 二分法
 */
public class Q27 {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        // 需要单独处理极限情况
        if (target > nums[n - 1]) {
            return n;
        }
        if (target < nums[0]) {
            return 0;
        }
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            int val = nums[mid];
            if (val == target) {
                return mid;
            }
            if (val > target) {
                right = mid - 1;
            }
            if (val < target) {
                left = mid + 1;
            }
        }

        int cur = nums[left];
        if (cur == target) {
            return left;
        } else if (cur > target) {
            return left;    // 不需要再+1
        } else {
            return left + 1;
        }
    }

    public static void main(String[] args) {
        Q27 q = new Q27();
        int[] nums = new int[]{1, 3, 5, 6};
        q.searchInsert(nums, 5);
    }
}


