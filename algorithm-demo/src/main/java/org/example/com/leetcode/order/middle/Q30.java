package org.example.com.leetcode.order.middle;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/k-closest-points-to-origin/
 * 973. 最接近原点的K个点
 */
public class Q30 {

    // 为什么是欧几里得距离的「平方」？这是因为欧几里得距离并不一定是个整数，在进行计算和比较时可能会引进误差；但它的平方一定是个整数，这样我们就无需考虑误差了。
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int dis1 = o1[0] * o1[0] + o1[1] * o1[1];
                int dis2 = o2[0] * o2[0] + o2[1] * o2[1];
                return (dis1 - dis2);
            }
        });
        return Arrays.copyOfRange(points, 0, k);
    }

    //  堆
    public int[][] kClosest2(int[][] points, int k) {
        // 大根堆，降序
        // 重写Comparator方法，自动维护顺序
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });

        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{
                    points[i][0] * points[i][0] + points[i][1] * points[i][1]
            });
        }

        int n = points.length;
        for (int i = k; i < n; i++) {
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (dist < pq.peek()[0]) {
                pq.poll();  // 删除该节点
                pq.offer(new int[]{dist, i});  // 存入平方和与索引
            }
        }

        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            ans[i] = points[pq.poll()[1]];
        }

        return ans;
    }
}


