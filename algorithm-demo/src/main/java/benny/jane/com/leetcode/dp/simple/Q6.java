package benny.jane.com.leetcode.dp.simple;

/**
 * 542. 01 矩阵
 * https://leetcode-cn.com/problems/01-matrix/
 */
public class Q6 {

    int m;
    int n;
    int[][] dp;

    // 运行超时
    public int[][] updateMatrix(int[][] mat) {
        m = mat.length;
        n = mat[0].length;

        dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = mat[i][j];
                if (cur == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dfs(mat, i, j, 0);
                }
            }
        }

        return dp;
    }

    private int dfs(int[][] mat, int x, int y, int len) {
        if (x >= m || y >= n || x < 0 || y < 0 || mat[x][y] < 0) {
            return Integer.MAX_VALUE;
        }
        if (mat[x][y] == 0) {
            return len;
        }
        if (dp[x][y] != 0) {
            return len + dp[x][y];
        }

        mat[x][y] = -1;

        int l1 = dfs(mat, x + 1, y, len + 1);
        int l2 = dfs(mat, x - 1, y, len + 1);
        int l3 = dfs(mat, x, y + 1, len + 1);
        int l4 = dfs(mat, x, y - 1, len + 1);
        int min = Math.min(l1, l2);
        min = Math.min(min, l3);
        min = Math.min(min, l4);
        mat[x][y] = 1;
        return min;
    }

    public static void main(String[] args) {
        Q6 q = new Q6();
        int[][] nums = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0},
                {1, 1, 1},
        };
        q.updateMatrix(nums);
    }
}


