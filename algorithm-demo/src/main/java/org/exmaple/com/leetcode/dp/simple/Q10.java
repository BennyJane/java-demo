package org.exmaple.com.leetcode.dp.simple;

/**
 * 787. K 站中转内最便宜的航班
 * https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/
 */
public class Q10 {

    private int ans = Integer.MAX_VALUE;
    private int src;
    private int dst;
    private int k;

    // 超出时间限制
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int len = flights.length;
        this.src = src;
        this.dst = dst;
        this.k = k;
        for (int i = 0; i < len; i++) {
            if (flights[i][0] == src) {
                dfs(flights, flights[i][1], 0, flights[i][2]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void dfs(int[][] flights, int start, int count, int cost) {
        if (start == dst) {
            ans = Math.min(ans, cost);
            return;
        }
        if (count >= k) {
            return;
        }
        for (int i = 0; i < flights.length; i++) {
            if (flights[i][0] == start) {
                int nextCost = cost + flights[i][2];
                int nextStart = flights[i][1];

                if (cost > ans) {
                    continue;
                }
                dfs(flights, nextStart, count + 1, nextCost);
            }
        }
    }

    public static void main(String[] args) {
        Q10 q = new Q10();
        int[][] nums = new int[][]{
//                {0, 1, 100},
//                {1, 2, 100},
//                {0, 2, 500}

                {3, 4, 4},
                {2, 5, 6},
                {4, 7, 10},
                {9, 6, 5},
                {7, 4, 4},
                {6, 2, 10},
                {6, 8, 6},
                {7, 9, 4},
                {1, 5, 4},
                {1, 0, 4},
                {9, 7, 3},
                {7, 0, 5},
                {6, 5, 8},
                {1, 7, 6},
                {4, 0, 9},
                {5, 9, 1},
                {8, 7, 3},
                {1, 2, 6},
                {4, 1, 5},
                {5, 2, 4},
                {1, 9, 1},
                {7, 8, 10},
                {0, 4, 2},
                {7, 2, 8}
        };
//        q.findCheapestPrice(3, nums, 0, 2, 0);
        q.findCheapestPrice(10, nums, 6, 0, 7);
    }
}


