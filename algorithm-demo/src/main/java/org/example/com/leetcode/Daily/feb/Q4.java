package org.example.com.leetcode.Daily.feb;

public class Q4 {
    public int countGoodRectangles(int[][] rectangles) {
        int cnt = 0, maxLen = 0;
        for (int[] tmp : rectangles) {
            int x = tmp[0], y = tmp[1];
            int min = Math.min(x, y);
            if (min == maxLen) {
                cnt++;
            } else if (min > maxLen) {
                maxLen = min;
                cnt = 1;
            } else {
                continue;
            }
        }

        return cnt;
    }
}
