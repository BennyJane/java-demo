package benny.jane.com.leetcode.array.middle;

/**
 * 238. 除自身以外数组的乘积
 */
public class Q68 {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        int[] left = new int[len];
        left[0] = 1;
        int[] right = new int[len];
        right[len - 1] = 1;
        // 统计每个索引左侧乘积
        for (int i = 1; i < len; i++) {
            left[i] = left[i] * nums[i - 1];
        }
        // 统计每个索引右侧乘积
        for (int i = len - 2; i >= 0; i--) {
            right[i] = right[i] * nums[i - 1];
        }

        for (int i = 0; i < len; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }
}


