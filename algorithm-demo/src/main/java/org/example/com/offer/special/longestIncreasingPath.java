package org.example.com.offer.special;

import java.util.Arrays;

//剑指 Offer II 112. 最长递增路径
public class longestIncreasingPath {
    // 记忆化搜索

    int m;
    int n;
    int[][] memo;
    int ans = 0;

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;

        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int step = dfs(matrix, i, j);
                ans = Math.max(ans, step);
            }
        }

        return ans;
    }

    private int dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= m || y >= n || y < 0) {
            return 0;
        }
        if (memo[x][y] != -1) {
            return memo[x][y];
        }

        int cur = grid[x][y];
        int maxStep = 1;
        if (x - 1 >= 0 && grid[x - 1][y] > cur) {
            int s = dfs(grid, x - 1, y) + 1;
            maxStep = Math.max(maxStep, s);
        }
        if (x + 1 < m && grid[x + 1][y] > cur) {
            int s = dfs(grid, x + 1, y) + 1;
            maxStep = Math.max(maxStep, s);
        }
        if (y - 1 >= 0 && grid[x][y - 1] > cur) {
            int s = dfs(grid, x, y - 1) + 1;
            maxStep = Math.max(maxStep, s);
        }
        if (y + 1 < n && grid[x][y + 1] > cur) {
            int s = dfs(grid, x, y + 1) + 1;
            maxStep = Math.max(maxStep, s);
        }
        memo[x][y] = maxStep;
        return maxStep;
    }
}
