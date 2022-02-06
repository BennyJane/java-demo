package org.example.com.offer.special;

import java.util.Deque;
import java.util.LinkedList;

public class updateMatrix {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] metric = new int[m][n];
        int[][] dir = new int[][]{
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        Deque<int[]> deque = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    deque.add(new int[]{i, j});
                }
            }
        }

        int step = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            step++;
            for (int i = 0; i < size; i++) {
                int[] cur = deque.pollFirst();
                for (int[] Vec : dir) {
                    int nx = cur[0] + Vec[0];
                    int ny = cur[1] + Vec[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && mat[nx][ny] == 1) {
                        metric[nx][ny] = step;
                        mat[nx][ny] = 0;
                        deque.add(new int[]{nx, ny});
                    }
                }

            }
        }

        return metric;
    }
}
