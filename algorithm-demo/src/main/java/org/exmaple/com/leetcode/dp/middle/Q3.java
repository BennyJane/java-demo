package org.exmaple.com.leetcode.dp.middle;

/**
 * 813. 最大平均值和的分组
 * https://leetcode-cn.com/problems/largest-sum-of-averages/
 */
public class Q3 {

    private double ans = Integer.MIN_VALUE;
    private int[] preSum;
    private int k;
    private int len;

    // 超出时间限制
    public double largestSumOfAverages(int[] nums, int k) {
        this.len = nums.length;
        this.k = k;
        // 计算前缀和
        int sum = 0;
        preSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }
        dfs(nums, 0, 0, 1);
        return ans;
    }

    private void dfs(int[] nums, int arrNum, double sum, int index) {
        if (index > len) {
            return;
        }
        if (arrNum == k - 1) {
            double res = (double) (preSum[len] - preSum[index - 1]) / (len - index + 1);
            ans = Math.max(ans, sum + res);
        } else {
            for (int i = index; i <= nums.length; i++) {
                double res = (double) (preSum[i] - preSum[index - 1]) / (i - index + 1);
                dfs(nums, arrNum + 1, sum + res, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        Q3 q = new Q3();
        int[] nums = new int[]{
                9, 1, 2, 3, 9
        };
        q.largestSumOfAverages(nums, 3);
    }
}


