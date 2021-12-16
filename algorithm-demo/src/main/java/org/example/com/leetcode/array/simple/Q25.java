package org.example.com.leetcode.array.simple;

import java.util.Arrays;

/**
 * 41. 缺失的第一个正数
 * https://leetcode-cn.com/problems/first-missing-positive/
 * 困难
 */
public class Q25 {
    // 最小正整数一定出自[1, n]范围内
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n];
        for (int num : nums) {
            if (num > 0 && num <= n) {
                visited[num - 1] = true;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i - 1]) {
                return i;
            }
        }
        return n + 1;
    }
}


