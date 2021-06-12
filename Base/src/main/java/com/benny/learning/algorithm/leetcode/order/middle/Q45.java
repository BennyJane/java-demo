package com.benny.learning.algorithm.leetcode.order.middle;

/**
 * 153. 寻找旋转排序数组中的最小值
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class Q45 {

    public int ans = Integer.MAX_VALUE; //最小值

    public int findMin(int[] nums) {
        int len = nums.length;
        dfs(nums, 0, len - 1);
        return ans;
    }

    public void dfs(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            int val = nums[mid];
            if (ans > val) {
                ans = val;
            }
            if (nums[0] <= val) {
                if (ans > nums[0]) {
                    ans = nums[0];
                }
                left = mid + 1;
            } else {
                if (ans > nums[right]) {
                    ans = nums[right];
                }
                right = mid - 1;
            }
        }
    }

    public static void main(String[] args) {
        Q45 q = new Q45();
//        int[] nums = {2,5,6,0,0,1,2};
//        System.out.println(q.search(nums, 0));
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println(q.findMin(nums1));
//        int[] nums2 = {1, 0, 1, 1, 1};
//        System.out.println(q.search(nums2, 0));
    }
}


