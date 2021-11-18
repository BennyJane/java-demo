package org.exmaple.com.match.Two54;

public class Q3 {

    private int ans = Integer.MIN_VALUE;
    private int WIDTH;
    private int HEIGHT;


    public int largestMagicSquare(int[][] grid) {
        HEIGHT = grid.length;
        WIDTH = grid[0].length;
        dfs(grid, 0, 0, 2);
        return ans;
    }

    private boolean dfs(int[][] grid, int row, int col, int k) {
        if (row + k > HEIGHT - 1 || col + k > WIDTH - 1) {
            if (k > ans) {
                ans = k;
            }
        }

        int[] sumArr = new int[8];
        // 列
        for (int i = 0; i < k; i++) {
            sumArr[i] += grid[row][col + i];
        }
        // 行
        for (int i = 0; i < k; i++) {
            sumArr[i + k] += grid[row + i][col];
        }
        for (int i = 0; i < k; i++) {
            sumArr[6] += grid[row + i][col + i];
        }
        for (int i = 0; i < k; i++) {
            sumArr[7] += grid[row + i][col + k - i];
        }


        return false;

    }
}


