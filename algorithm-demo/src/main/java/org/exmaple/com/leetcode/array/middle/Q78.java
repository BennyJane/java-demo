package org.exmaple.com.leetcode.array.middle;

/**
 * 713. 乘积小于K的子数组
 * https://leetcode-cn.com/problems/subarray-product-less-than-k/
 */
public class Q78 {
    // 连续子数组：固定一边，动态修改另一边界
    // 对于长度为n的数组，当一侧边界固定时，连续子数组数量为： n(长度1~n)
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }

        int prod = 1;
        int ans = 0;
        int left = 0;
        for (int right= 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) {
                prod /= nums[left];
                left++;
            }
            ans+= (right - left +1);
        }

        return ans;
    }

    public static void main(String[] args) {
        Q78 q = new Q78();
        int[] nums = new int[]{10, 5, 2, 6};
        q.numSubarrayProductLessThanK(nums, 100);
    }
}


