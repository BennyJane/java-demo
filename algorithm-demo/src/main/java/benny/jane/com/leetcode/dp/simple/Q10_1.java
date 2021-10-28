package benny.jane.com.leetcode.dp.simple;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 787. K 站中转内最便宜的航班
 * https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/
 */
public class Q10_1 {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int ans = Integer.MAX_VALUE;
        int depth = 0;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(dst, 0));
        while (!queue.isEmpty() && depth <= k) {
            int size = queue.size();    // FIXME 不能放到for语句种执行，大小变动
            for (int j = 0; j < size; j++) {
                Pair<Integer, Integer> pair = queue.poll();
                for (int i = 0; i < flights.length; i++) {
                    if (flights[i][1] == pair.getKey()) {
                        int cost = flights[i][2] + pair.getValue();
                        if (cost > ans) {
                            continue;
                        }
                        if (flights[i][0] == src) {
                            ans = Math.min(ans, cost);
                        } else {    // 没有到达起点位置
                            queue.offer(new Pair<>(flights[i][0], cost));
                        }
                    }
                }
            }
            depth++;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        Q10_1 q = new Q10_1();
        int[][] nums = new int[][]{
//                {0, 1, 100},
//                {1, 2, 100},
//                {0, 2, 500}
                {0, 1, 20},
                {1, 2, 20},
                {2, 3, 30},
                {3, 4, 30},
                {4, 5, 30},
                {5, 6, 30},
                {6, 7, 30},
                {7, 8, 30},
                {8, 9, 30},
                {0, 2, 9999},
                {2, 4, 9998},
                {4, 7, 9997}

        };
//        q.findCheapestPrice(3, nums, 0, 2, 0);
        q.findCheapestPrice(10, nums, 0, 9, 4);
    }
}


