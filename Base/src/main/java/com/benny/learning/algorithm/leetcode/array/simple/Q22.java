package com.benny.learning.algorithm.leetcode.array.simple;

/**
 * 167. 两数之和 II - 输入有序数组
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class Q22 {
    public int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        for (int slow = 1; slow < len + 1; slow++) {
            for (int fast = slow + 1; fast < len; fast++) {
                int sum = numbers[slow - 1] + numbers[fast - 1];
                if (sum == target) {
                    return new int[]{slow, fast};
                }
                if (sum > target) {
                    break;
                }
            }
        }
        return new int[]{0, 1};
    }

    public int[] twoSum2(int[] numbers, int target) {
        int len = numbers.length;
        int left = 1, right = len;
        while (left < right) {
            int sum = numbers[left - 1] + numbers[right - 1];
            if (sum == target) {
                break;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{left, right};
    }
}


