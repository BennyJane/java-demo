package com.benny.learning.algorithm.leetcode.array.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 */
public class Q24 {
    // 暴力方法： 利用集合的来判断；排序后再逐个遍历

    // [1, n] 范围的数值减去1后，恰好映射到数组的索引[0, n-1]
    // 利用原数组的索引作为参考 [0, n-1]
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int cur : nums) {
            int index = Math.abs(cur) - 1;
            if (nums[index] > 0) {
                nums[index] = -1 * nums[index]; // 将当前索引对应位置的数值修改为负数，标记为已存在该值
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] > 0) {
                list.add(i);
            }
        }
        return list;
    }
}


