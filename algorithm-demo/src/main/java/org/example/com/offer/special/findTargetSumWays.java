package org.example.com.offer.special;

import java.util.Arrays;

public class findTargetSumWays {
    private int[] cache;

    public int findTargetSumWays(int[] nums, int target) {
        cache = new int[target + 1];
        Arrays.fill(cache, -1);
        cache[0] = 1;
        return dfs(nums, target);
    }

    private int dfs(int[] nums, int rest) {
        if (rest < 0) return 0;
        if (cache[rest] != -1) return cache[rest];
        int cnt = 0;
        for (int n : nums) {
            cnt += dfs(nums, rest - n);
        }
        cache[rest] = cnt;
        return cnt;
    }
}
