package org.example.com.leetcode.array.middle;


import java.util.Arrays;

/**
 * 532. 数组中的 k-diff 数对
 * https://leetcode-cn.com/problems/k-diff-pairs-in-an-array/
 */
public class Q51 {
    public int findPairs(int[] nums, int k) {
        int ans = 0;
        int len = nums.length;
        int left = 0, right = 1;
        Arrays.sort(nums);
        while (right < len && left < right) {
            if (Math.abs(nums[right] - nums[left]) < k) {
                right++;
            } else if (Math.abs(nums[right] - nums[left]) == k) {
                ans++;
                while (left < right - 1 && nums[left] == nums[left + 1]) {
                    left++;
                }
                while (right < len - 1 && nums[right] == nums[right + 1]) {
                    right++;
                }
                right++;
            } else {
                if (right - left == 1 && right < len) {
                    right++;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Q51 q = new Q51();
        int[] nums = new int[]{1,2,4,4,3,3,0,9,2,3};
//        int[] nums = new int[]{1, 1, 1, 2, 2};
        int k = 2;
        q.findPairs(nums, k);

    }
}


