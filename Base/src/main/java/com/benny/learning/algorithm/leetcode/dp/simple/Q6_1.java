package com.benny.learning.algorithm.leetcode.dp.simple;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 542. 01 矩阵
 * https://leetcode-cn.com/problems/01-matrix/
 */
public class Q6_1 {

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];
        boolean[][] visited = new boolean[m][n];

        // 表示：相邻四个位置的计算方式
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 队列作为栈使用
        // 将所有0添加到队列中，同时，标注为已访问
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }


        // 逐层标记
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int d = 0; d < 4; d++) {
                // 计算相邻位置索引
                int X = x + dirs[d][0];
                int Y = y + dirs[d][1];
                if (X >= 0
                        && X < m
                        && Y >= 0
                        && Y < n
                        && !visited[X][Y]
                ) {
                    dp[X][Y] = dp[x][y] + 1;
                    queue.offer(new int[]{X, Y});
                    visited[X][Y] = true;
                }
            }
        }

        return dp;
    }

    public static void main(String[] args) {
        Q6_1 q = new Q6_1();
        int[][] nums = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0},
                {1, 1, 1},
        };
        q.updateMatrix(nums);
    }
}


