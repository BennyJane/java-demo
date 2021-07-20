package com.benny.learning.algorithm.leetcode.dp.middle;

/**
 * 576. 出界的路径数
 * https://leetcode-cn.com/problems/out-of-boundary-paths/
 */
public class Q8 {
    // 广度优先

    int mod = (int) 1e9 + 7;
    int rowNum, colNum, stepLimit;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        rowNum = m;
        colNum = n;
        stepLimit = maxMove;


        // f[i][j] 代表从 idx 为 i 的位置出发，移动步数不超过 j 的路径数量
        // 将坐标x,y 构造为一个参数; TODO 降维的一种操作
        int[][] f = new int[m * n][maxMove + 1];

        // 初始化边缘格子的路径数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) add(i, j, f);
                if (i == m - 1) add(i, j, f);
                if (j == 0) add(i, j, f);
                if (j == n - 1) add(i, j, f);
            }
        }

        // 定义可移动的四个方向
        int[][] dirs = new int[][]{
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1},
        };

        // 从小到大枚举：可移动步数
        for (int step = 1; step <= stepLimit; step++) {
            // 枚举所有位置
            for (int k = 0; k < m * n; k++) {
                int x = parseIdx(k)[0];
                int y = parseIdx(k)[1];
                for (int[] d : dirs) {
                    int nextX = x + d[0];
                    int nextY = y + d[1];
                    // 如果位置有「相邻格子」，则「相邻格子」参与状态转移
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
                        f[k][step] += f[getIndex(nextX, nextY)][step - 1];
                        f[k][step] %= mod;
                    }
                }
            }
        }

        return f[getIndex(startRow, startColumn)][maxMove];
    }

    private void add(int x, int y, int[][] f) {
        int idx = getIndex(x, y);
        for (int step = 1; step <= stepLimit; step++) {
            f[idx][step]++;
        }
    }

    private int getIndex(int x, int y) {
        // 计算从0,0 到该位置的长度
        return x * colNum + y;
    }

    // 将 index 解析回 (x, y)
    int[] parseIdx(int idx) {
        return new int[]{idx / colNum, idx % colNum};
    }
}



