package org.example.com.leetcode.array.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. 插入区间
 * https://leetcode-cn.com/problems/insert-interval/
 */
public class Q50 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> list = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {  // 判断条件可以更换为 newInterval的两个值
                if (!placed) {
                    // 在newInterval区间右侧存在其他无交的区间时，需要先插入newInterval或合并后新区间
                    list.add(new int[]{left, right});
                    placed = true;
                }
                list.add(interval);
            } else if (interval[1] < left) {
                list.add(interval);
            } else {
                left = Math.min(left, interval[0]);
                right = Math.min(right, interval[1]);
            }
        }

        if (!placed) {
            list.add(new int[]{left, right});
        }
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); ++i) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}


