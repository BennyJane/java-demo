package org.example.com.offer.special;

import java.util.Deque;
import java.util.LinkedList;

public class maxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int area = 0;

        int m = grid.length, n = grid[0].length;
        int[][] dir = new int[][]{
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                // 广度优先遍历，统计岛屿面积

                Deque<int[]> deque = new LinkedList<>();
                deque.add(new int[]{i, j});
                // FIXME 标记已经访问的陆地
                grid[i][j] = 0;
                int tmp = 0;
                while (!deque.isEmpty()) {
                    int[] pos = deque.pollFirst();
                    tmp += 1;
                    for (int[] mv : dir) {
                        int nX = pos[0] + mv[0];
                        int nY = pos[1] + mv[1];
                        if (nX >= 0 && nX < m && nY >= 0 && nY < n
                                && grid[nX][nY] == 1) {
                            deque.add(new int[]{nX, nY});
                            // FIXME 标记已经访问的陆地
                            grid[nX][nY] = 0;
                        }
                    }
                }
                area = Math.max(area, tmp);
            }
        }

        return area;
    }
}
