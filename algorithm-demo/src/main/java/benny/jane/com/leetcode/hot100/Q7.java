package benny.jane.com.leetcode.hot100;

import sun.awt.windows.WPrinterJob;

import javax.swing.*;

/**
 * 221. 最大正方形
 * https://leetcode-cn.com/problems/maximal-square/
 */
public class Q7 {

    private int ans = 0;

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int cur = matrix[row][col];
                if (cur == '0') {
                    continue;
                }
                ans = Math.max(ans, 1);
                // 起始点为1
                int maxWidth = Math.min(m - row, n - col);
                for (int w = maxWidth; w >= 0; w--) {
                    if (check(matrix, row, col, w)) {
                        ans = Math.max(ans, w * w);
                    }
                }
            }
        }

        return ans;
    }

    private boolean check(char[][] matrix, int x, int y, int width) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[x + i][y + j] == '0') {
                    return false;
                }
            }
        }
        return true;
    }
}


