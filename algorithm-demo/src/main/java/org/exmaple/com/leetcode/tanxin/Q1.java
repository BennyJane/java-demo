package org.exmaple.com.leetcode.tanxin;

import com.benny.learning.algorithm.Q;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/smallest-range-ii/
 * 910. 最小差值 II
 */
public class Q1 {
    // FIXME 部分测试用例不通过
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int gap = nums[len - 1] - nums[0];
        if (k >= gap) {
            return gap;
        }

        int min = Math.min(nums[0] + k, nums[len - 1] - k);
        int max = Math.max(nums[0] + k, nums[len - 1] - k);
        int left = 1;
        int right = len - 2;
        while (left <= right) {
            int leftVal = nums[left];
            if (leftVal + k > max && leftVal - k < min) {
                if (leftVal + k - max >= min - (leftVal - k)) {
                    min = leftVal - k;
                } else {
                    max = leftVal + k;
                }
            }
            int rightVal = nums[right];
            if (rightVal + k > max && rightVal - k < min) {
                if (rightVal + k - max >= min - (rightVal - k)) {
                    min = rightVal - k;
                } else {
                    max = rightVal + k;
                }
            }
            left++;
            right--;
        }
        return Math.min(max - min, gap);
    }

    public int smallestRangeII2(int[] nums, int K) {
        int N = nums.length;
        Arrays.sort(nums);
        int ans = nums[N-1] - nums[0];

        for (int i = 0; i < nums.length - 1; ++i) {
            int a = nums[i], b = nums[i+1];
            int high = Math.max(nums[N-1] - K, a + K);
            int low = Math.min(nums[0] + K, b - K);
            ans = Math.min(ans, high - low);
        }
        return ans;
    }

    public static void main(String[] args) {
        Q1 q = new Q1();
//        int[] nums = new int[]{7, 8, 8, 5, 2};
//        int k = 4;
        int[] nums = new int[]{8038,9111,5458,8483,5052,9161,8368,2094,8366,9164,53,7222,9284,5059,4375,2667,2243,5329,3111,5678,5958,815,6841,1377,2752,8569,1483,9191,4675,6230,1169,9833,5366,502,1591,5113,2706,8515,3710,7272,1596,5114,3620,2911,8378,8012,4586,9610,8361,1646,2025,1323,5176,1832,7321,1900,1926,5518,8801,679,3368,2086,7486,575,9221,2993,421,1202,1845,9767,4533,1505,820,967,2811,5603,574,6920,5493,9490,9303,4648,281,2947,4117,2848,7395,930,1023,1439,8045,5161,2315,5705,7596,5854,1835,6591,2553,8628};
        int k = 4643;
        q.smallestRangeII(nums, k);
    }
}


