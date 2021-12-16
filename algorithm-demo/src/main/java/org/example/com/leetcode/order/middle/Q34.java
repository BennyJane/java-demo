package org.example.com.leetcode.order.middle;


import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode-cn.com/problems/remove-covered-intervals/submissions/
 * 1288. 删除被覆盖区间
 * 贪心算法
 */
public class Q34 {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 排序：起点升序，终点降序
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });

        int count = 0;
        int end, prev_end = 0;
        for (int[] curr : intervals) {
            end = curr[1];
            // 判断：非覆盖区间
            if (prev_end > end) {
                count++;
                prev_end = end;
            }
            // 其他情况：都是被覆盖区间
        }
        return count;
    }

}


