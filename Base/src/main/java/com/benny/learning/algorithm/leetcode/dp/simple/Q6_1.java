package com.benny.learning.algorithm.leetcode.dp.simple;

/**
 * 542. 01 矩阵
 * https://leetcode-cn.com/problems/01-matrix/
 */
public class Q6_1 {

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


