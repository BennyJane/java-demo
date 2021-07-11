package com.benny.learning.algorithm.leetcode.dp.middle;

/**
 * 327. 区间和的个数
 * https://leetcode-cn.com/problems/count-of-range-sum/
 */
public class Q2_4 {
    // 暴力求解： 遍历所有子区间，然后计算区间和

    private long lower;
    private long upper;

    public int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        if (len < 1) {
            return 0;
        }

        this.lower = lower;
        this.upper = upper;
        // 前缀和
        int[] temp = new int[len + 1];
        for (int i = 0; i < len; i++) {
            temp[i + 1] = nums[i] + temp[i];
        }

        int ans = countRangeSum(nums, 0, len - 1, temp);
        return ans;
    }

    private int countRangeSum(int[] nums, int left, int right, int[] temp) {
        if (left >= right) {
            if (nums[left] >= lower && nums[left] <= upper) {
                return 1;
            }
            return 0;
        }

        int mid = left + (right - left) / 2;
        int leftCount = countRangeSum(nums, left, mid, temp);
        int rightCount = countRangeSum(nums, mid + 1, right, temp);

        int mergeCount = mergeAndCount(nums, left, mid, right, temp);

        return leftCount + rightCount + mergeCount;
    }

    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        // 首先统计下标对的数量
        int i = left;
        int j = mid + 1;

        int count = 0;
        while (i <= mid) {
            while (j <= right
                    && temp[j+1] - temp[i+1] >= lower
                    && temp[j+1] - temp[i+1] <= upper
            ) {
                j++;
            }
            count += j - mid - 1;
            i++;
        }
        return count;
    }

    public static void main(String[] args) {
        Q2_4 q = new Q2_4();
        int[] nums = new int[]{
//                -2, 5, -1
                -1, 1
        };
//        q.countRangeSum(nums, -2, 2);
        q.countRangeSum(nums, 0, 0);
    }
}


