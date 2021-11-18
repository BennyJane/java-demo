package org.exmaple.com.leetcode.array.simple;

/**
 * 503. 下一个更大元素 II
 * https://leetcode-cn.com/problems/next-greater-element-ii/
 */
public class Q35 {
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];

        for (int i = 0; i < len; i++) {
            int cur = nums[i];
            int larger = i;
            while (larger < i + len && nums[larger % len] <= cur) {
                larger++;
            }
            if (larger >= i + len) {
                ans[i] = -1;
            } else {
                ans[i] = nums[larger];
            }
        }

        return nums;
    }

    // TODO 优化效率
}


