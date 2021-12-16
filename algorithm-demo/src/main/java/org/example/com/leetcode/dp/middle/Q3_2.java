package org.example.com.leetcode.dp.middle;

/**
 * 813. 最大平均值和的分组
 * https://leetcode-cn.com/problems/largest-sum-of-averages/
 */
public class Q3_2 {

    private double ans = Integer.MIN_VALUE;
    private int[] preSum;
    private int k;

    // 超出时间限制
    public double largestSumOfAverages(int[] nums, int k) {

        return ans;
    }


    public static void main(String[] args) {
        Q3_2 q = new Q3_2();
        int[] nums = new int[]{
                9, 1, 2, 3, 9
        };
        q.largestSumOfAverages(nums, 3);
    }
}


