package org.example.com.leetcode.Daily.feb;

import sun.text.normalizer.CharacterIteratorWrapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Q5 {

    int maxAns = 0;
    int m, n;
    ArrayList<int[]> startPos = new ArrayList<>();

    public int getMaximumGold(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = grid[i][j];
                if (cur != 0) {
                    dfs(grid, i, j, cur);
                }
            }
        }

        return maxAns;
    }

    private void dfs(int[][] board, int x, int y, int val) {
        if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] <= 0) {
            maxAns = Math.max(maxAns, val);
            return;
        }
        int cur = board[x][y];
        val += cur;
        board[x][y] = 0;
        dfs(board, x + 1, y, val);
        dfs(board, x - 1, y, val);
        dfs(board, x, y + 1, val);
        dfs(board, x, y - 1, val);
        board[x][y] = cur;
    }


    private void bfs(int[][] board, int x, int y) {
        int cur = board[x][y];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, cur});

        int[][] dir = new int[][]{
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1},
        };

        while (!queue.isEmpty()) {
            int[] start = queue.poll();
            for (int[] mv : dir) {
                int nx = start[0] + mv[0], ny = start[1] + mv[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && board[nx][ny] != 0) {
                    continue;
                }
            }
        }
    }
}
