package org.exmaple.com.leetcode.dp.middle;

/**
 * 493. 翻转对
 * https://leetcode-cn.com/problems/reverse-pairs/
 */
public class Q2_3 {
    // 基于归并排序的判断
    public int reversePairs(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }

        int[] temp = new int[len];
        int ans = reversePairs(nums, 0, len - 1, temp);
        return ans;
    }

    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int leftCount = reversePairs(nums, left, mid, temp);
        int rightCount = reversePairs(nums, mid + 1, right, temp);

        int mergeCount = mergeAndCount(nums, left, mid, right, temp);
        return leftCount + rightCount + mergeCount;
    }

    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        // 单独处理判断条件
        int count = 0;
        while (i <= mid) {
            while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                j++;
            }
            count += j - mid - 1;
            i++;
        }

        i = left;
        j = mid + 1;
        // 合并两个区间，完成排序功能
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Q2_3 q = new Q2_3();
        int[] nums = new int[]{
//                1, 3, 2, 3, 1
//                5,4,3,2,1
//                -5, -5
//                2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647
                2147483647, 2147483647, -2147483647, -2147483647, -2147483647, 2147483647
//                -5, -5, -5
        };
        q.reversePairs(nums);
    }
}


