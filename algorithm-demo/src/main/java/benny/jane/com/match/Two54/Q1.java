package benny.jane.com.match.Two54;

import com.benny.learning.algorithm.Q;

import javax.swing.plaf.basic.BasicArrowButton;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Q1 {
    public boolean isCovered(int[][] ranges, int left, int right) {
        // 排序
        Arrays.sort(ranges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        int index = 0;
        int len = ranges.length;
        for (int i = left; i <= right; i++) {
            while (index < len) {
                int[] arr = ranges[index];
                if (i < arr[0]) {
                    return false;
                } else if (i > arr[1]) {
                    if (index == len - 1) {  // 最后一个区间，应该直接跳出
                        return false;
                    } else {
                        index++;
                    }
                } else {
                    break;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Q1 q = new Q1();
        int[][] nums = new int[][]{{1, 10}, {10, 20}};
        boolean res = q.isCovered(nums, 21, 21);
        System.out.println(res);
    }
}
