package org.example.com.leetcode.dp.middle;

/**
 * 1043. 分隔数组以得到最大和
 * https://leetcode-cn.com/problems/partition-array-for-maximum-sum/
 */
public class Q7 {
    // 索引i位置处： 只需要考虑i+k范围内的最大值
    // 滑动窗口 || 动态规划
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int len = arr.length;
        // 表示i位置处可取的最大值
        int[] dp = new int[len];
        int max = 0;
        if (len <= k) {
            for (int n : arr) {
                max = Math.max(max, n);
            }
            return len * max;
        }
        int left = 0;
        for (int i = 0; i < k; i++) {
            if (arr[i] >= max) {
                max = arr[i];
            }
            max = Math.max(max, arr[i]);
        }
        int right = k;
        while (left < len) {
            dp[left] = max;
            left++;
            if (right < len && arr[right] > max) {
                max = arr[right];
                right++;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        Q7 q = new Q7();
        int[] nums = new int[]{
                1, 15, 7, 9, 2, 5, 10
        };
        q.maxSumAfterPartitioning(nums, 3);
    }
}


