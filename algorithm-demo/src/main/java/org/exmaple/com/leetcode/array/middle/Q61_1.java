package org.exmaple.com.leetcode.array.middle;

/**
 * 581. 最短无序连续子数组
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 */
public class Q61_1 {
    // 需要考虑重复值
    public int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                flag = true;
            }
            if (flag) {
                min = Math.min(min, nums[i]);
            }
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                flag = true;
            }
            if (flag) {
                max = Math.max(max, nums[i]);
            }
        }
        int left, right;
        for (left = 0; left < nums.length; left++) {
            if (min < nums[left]) {
                break;
            }
        }
        for (right = nums.length; right >= 0; right--) {
            if (max > nums[right]) {
                break;
            }
        }

        return right - left < 0 ? 0 : right - left + 1;
    }

    public static void main(String[] args) {
        Q61_1 q = new Q61_1();
//        int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        int[] nums = new int[]{1, 2, 3, 4};
//        int[] nums = new int[]{1, 3, 2, 2, 2};
//        int[] nums = new int[]{1, 2, 3, 5, 4};
//        int[] nums = new int[]{2, 1};
        q.findUnsortedSubarray(nums);
    }
}


