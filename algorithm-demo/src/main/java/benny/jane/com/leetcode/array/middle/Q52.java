package benny.jane.com.leetcode.array.middle;

/**
 * 560. 和为K的子数组
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/
 */
public class Q52 {
    // 前缀和
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] preSum = new int[len + 1];    // 长度变化

        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int ans = 0;
        // 起始位置
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (preSum[i] - preSum[j] == k) {
                    ans++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Q52 q = new Q52();
//        int[] nums = new int[]{1,2,4,4,3,3,0,9,2,3};
        int[] nums = new int[]{1, 1, 1};
        int k = 2;
        q.subarraySum(nums, k);

    }
}


