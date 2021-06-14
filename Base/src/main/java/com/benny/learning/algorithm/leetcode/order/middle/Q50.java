package com.benny.learning.algorithm.leetcode.order.middle;

/**
 * 48. 旋转图像
 * https://leetcode-cn.com/problems/rotate-image/
 */
public class Q50 {
    // https://leetcode-cn.com/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
    public void rotate(int[][] matrix) {
        // 计算整个方形的宽度
        int width = matrix.length;
        int[][] newMatrix = new int[width][width];
        // 每行数据看做整体
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < width; col++) {
                int newRow = col;
                int newCol = width - 1 - row;
                // 填入新的位置
                newMatrix[newRow][newCol] = matrix[row][col];
            }
        }

        // 将数据更新到原数组内
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < width; col++) {
                int newRow = col - (width - 1);
                int newCol = row;
                // 填入新的位置
                matrix[newRow][newCol] = newMatrix[row][col];
            }
        }
    }

    public void rotate2(int[][] matrix) {

        
        return;
    }
}


