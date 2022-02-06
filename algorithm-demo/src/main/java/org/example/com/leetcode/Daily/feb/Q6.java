package org.example.com.leetcode.Daily.feb;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/sum-of-unique-elements/
// 1748.唯一元素的和
public class Q6 {
    public int sumOfUnique1(int[] nums) {
        int ans = 0;
        int[] dp = new int[101];
        for (int c : nums) {
            dp[c] += 1;
        }

        for (int i = 1; i <= 100; i++) {
            int c = dp[i];
            if (c == 1) {
                ans += i;
            }
        }

        return ans;
    }

    // 一次遍历： 先排序后遍历 or 使用map记录
    public int sumOfUnique(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> state = new HashMap<>();
        for (int n : nums) {
            if (!state.containsKey(n)) {
                ans += n;
                state.put(n, 1);
            } else if (state.get(n) == 1) {
                ans -= n;
                state.put(n, 2);
            }
        }
        return ans;
    }
}
