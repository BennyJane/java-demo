package org.example.com.leetcode.hot100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 983. 最低票价
 * https://leetcode-cn.com/problems/minimum-cost-for-tickets/
 */
public class Q8_1 {

    int[] costs;
    Integer[] memo;
    Set<Integer> dayset;

    // dp[i]: 表示从第i天开始到一年结束，需要花费的钱
    public int mincostTickets(int[] days, int[] costs) {
        this.costs = costs;
        memo = new Integer[366];
        dayset = new HashSet<>();
        for (int d : days) {
            dayset.add(d);
        }
        return 0;
    }

    public int dp(int i) {
        if (i > 365) {
            return 0;
        }
        if (memo[i] != null) {
            return memo[i];
        }
        if (dayset.contains(i)) {
            memo[i] = Math.min(
                    Math.min(dp(i + 1) + costs[0], dp(i + 7) + costs[1]),
                    dp(i + 30) + costs[2]
            );
        } else {
            memo[i] = dp(i + 1);
        }
        return memo[i];
    }

}


