package org.example.com.leetcode.Daily.feb;

import java.util.LinkedList;
import java.util.Queue;

//1020. 飞地的数量
public class Q12 {
    // 多源起点：广度优先遍历
    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dir = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                grid[i][0] = 2;
                queue.add(new int[]{i, 0});
            }
            if (grid[i][n - 1] == 1) {
                grid[i][n - 1] = 2;
                queue.add(new int[]{i, n - 1});
            }

        }

        for (int i = 1; i < n - 1; i++) {
            if (grid[0][i] == 1) {
                grid[0][i] = 0;
                queue.add(new int[]{0, i});
            }
            if (grid[m - 1][i] == 1) {
                grid[m - 1][i] = 0;
                queue.add(new int[]{m - 1, i});
            }

        }

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            for (int[] mv : dir) {
                int nx = x + mv[0], ny = y + mv[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] != 1) {
                    continue;
                }
                grid[nx][ny] = 0;
                queue.add(new int[]{nx, ny});
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    ans++;
                }
            }
        }

        return ans;
    }

    // 深度优先遍历: 依然要从四条边上，满足条件的点出发，进行遍历
    public int numEnclaves2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = grid[i][j];
                if (cur == 0) {
                    continue;
                }
                // 处理位于四周边上，且为1的陆地
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    if (grid[i][j] == 1) {
                        dfs(grid, i, j);
                    }
                }
            }
        }
        // 遍历，寻找陆地数量
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0) {
            return;
        }

        grid[x][y] = 1;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
    }


    // 并查集
    class Solution {
        public int numEnclaves(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            UnionFind uf = new UnionFind(grid);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        int index = i * n + j;
                        if (j + 1 < n && grid[i][j + 1] == 1) {
                            uf.union(index, index + 1);
                        }
                        if (i + 1 < m && grid[i + 1][j] == 1) {
                            uf.union(index, index + n);
                        }
                    }
                }
            }
            int enclaves = 0;
            for (int i = 1; i < m - 1; i++) {
                for (int j = 1; j < n - 1; j++) {
                    if (grid[i][j] == 1 && !uf.isOnEdge(i * n + j)) {
                        enclaves++;
                    }
                }
            }
            return enclaves;
        }
    }

    class UnionFind {
        private int[] parent;
        private boolean[] onEdge;
        private int[] rank;

        public UnionFind(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            parent = new int[m * n];
            onEdge = new boolean[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        int index = i * n + j;
                        parent[index] = index;
                        if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                            onEdge[index] = true;
                        }
                    }
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                    onEdge[rootx] |= onEdge[rooty];
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                    onEdge[rooty] |= onEdge[rootx];
                } else {
                    parent[rooty] = rootx;
                    onEdge[rootx] |= onEdge[rooty];
                    rank[rootx]++;
                }
            }
        }

        public boolean isOnEdge(int i) {
            return onEdge[find(i)];
        }
    }
}
