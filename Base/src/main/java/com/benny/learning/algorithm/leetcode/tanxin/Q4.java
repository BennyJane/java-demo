package com.benny.learning.algorithm.leetcode.tanxin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/
 * 1011. 在 D 天内送达包裹的能力
 */
public class Q4 {
    // 将数据分为连续的n个子数组，求最大值最小的情况
    // TODO 动态规划（不可行）； 递归（暴力求解, 必然超时）
    // 二分法  + 贪心
    public int shipWithinDays(int[] weights, int days) {
        // 确定二分查找左右边界
        int left = Arrays.stream(weights).max().getAsInt(); // 数组内最大值
        int right = Arrays.stream(weights).sum();   // 一次运输
        while (left < right) {
            int mid = (left + right) / 2;
            // need 为需要运送的天数; TODO need 从1开始，
            // cur 为当前这一天已经运送的包裹重量之和
            int need = 1, cur = 0;
            for (int weight : weights) {
                if (cur + weight > mid) {
                    ++need; // 加1后，表示后续weight 至少还要运输一次
                    cur = 0;
                }
                cur += weight;
            }
            // TODO 根据实际匀速天数，调整左右边界
            if (need <= days) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        Q4 q = new Q4();
        int[] nums = new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        };

        q.shipWithinDays(nums, 5);
    }
}


