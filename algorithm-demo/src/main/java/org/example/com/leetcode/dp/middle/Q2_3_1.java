package org.example.com.leetcode.dp.middle;

/**
 * 493. 翻转对
 * https://leetcode-cn.com/problems/reverse-pairs/
 */
public class Q2_3_1 {
    // 基于归并排序的判断
    public int reversePairs(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        return reversePairsRecursive(nums, 0, nums.length - 1);
    }

    public int reversePairsRecursive(int[] nums, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            int n1 = reversePairsRecursive(nums, left, mid);
            int n2 = reversePairsRecursive(nums, mid + 1, right);
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            int j = mid + 1;
            while (i <= mid) {
                while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                    j++;
                }
                ret += j - mid - 1;
                i++;
            }

            // 随后合并两个排序数组
            int[] sorted = new int[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = nums[p2++];
                } else if (p2 > right) {
                    sorted[p++] = nums[p1++];
                } else {
                    if (nums[p1] < nums[p2]) {
                        sorted[p++] = nums[p1++];
                    } else {
                        sorted[p++] = nums[p2++];
                    }
                }
            }
            for (int k = 0; k < sorted.length; k++) {
                nums[left + k] = sorted[k];
            }
            return ret;
        }
    }


    public static void main(String[] args) {
        Q2_3_1 q = new Q2_3_1();
        int[] nums = new int[]{
//                1, 3, 2, 3, 1
//                5,4,3,2,1
//                -5, -5
//                2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647
                2147483647, 2147483647, -2147483647, -2147483647, -2147483647, 2147483647
//                -5, -5, -5
        };
        System.out.println(q.reversePairs(nums));
    }
}


