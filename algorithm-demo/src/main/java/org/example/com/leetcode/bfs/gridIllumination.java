package org.example.com.leetcode.bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

////https://leetcode-cn.com/problems/grid-illumination/
public class gridIllumination {
    Set<Integer> pos = new HashSet<>();
    Map<Integer, Integer> rowDir = new HashMap<>();
    Map<Integer, Integer> colDir = new HashMap<>();
    Map<Integer, Integer> leftDir = new HashMap<>();
    Map<Integer, Integer> rightDir = new HashMap<>();
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        int cnt = queries.length;
        int[] ans = new int[cnt];

        for (int[] p : lamps) {
            int posNum = p[0] * n + p[1];
            // 重复位置的灯，直接跳过，因为会被一次性全关掉
            if (pos.contains(posNum)) {
                continue;
            }
            pos.add(posNum);
            int x = p[0], y = p[1];
            rowDir.put(x, rowDir.getOrDefault(x, 0) + 1);
            colDir.put(y, colDir.getOrDefault(y, 0) + 1);
            leftDir.put(x - y, leftDir.getOrDefault(x - y, 0) + 1);
            rightDir.put(y + x, rightDir.getOrDefault(y + x, 0) + 1);
        }

        // 需要讨论九个位置
        int[][] dir = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {-1, -1}, {-1, 1}, {1, -1},
                // 需要包含中心位置
                {0, 0},
        };
        for (int i = 0; i < cnt; i++) {
            int x = queries[i][0], y = queries[i][1];
            if (rowDir.containsKey(x) || colDir.containsKey(y) || leftDir.containsKey(x - y) || rightDir.containsKey(x + y)) {
                ans[i] = 1;
            }

            for (int[] mv : dir) {
                int nx = x + mv[0], ny = y + mv[1];
                int posNum = nx * n + ny;
                // 必须使用该判断
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                if (pos.contains(posNum)) {
                    pos.remove(posNum);
                    if (rowDir.containsKey(nx)) {
                        if (rowDir.get(nx) == 1) {
                            rowDir.remove(nx);
                        } else {
                            rowDir.put(nx, rowDir.get(nx) - 1);
                        }
                    }
                    if (colDir.containsKey(ny)) {
                        if (colDir.get(ny) == 1) {
                            colDir.remove(ny);
                        } else {
                            colDir.put(ny, colDir.get(ny) - 1);
                        }
                    }

                    if (leftDir.containsKey(nx - ny)) {
                        if (leftDir.get(nx - ny) == 1) {
                            leftDir.remove(nx - ny);
                        } else {
                            leftDir.put(nx - ny, leftDir.get(nx - ny) - 1);
                        }
                    }

                    if (rightDir.containsKey(ny + nx)) {
                        if (rightDir.get(ny + nx) == 1) {
                            rightDir.remove(ny + nx);
                        } else {
                            rightDir.put(ny + nx, rightDir.get(ny + nx) - 1);
                        }
                    }
                }

            }
        }

        return ans;
    }


    Map<Integer, Integer> posCnt = new HashMap<>();

    public int[] gridIllumination1(int n, int[][] lamps, int[][] queries) {
        int cnt = queries.length;
        int[] ans = new int[cnt];

        for (int[] p : lamps) {
            int posNum = p[0] * n + p[1];
            if (posCnt.containsKey(posNum)) {
                continue;
            }
            posCnt.put(posNum, posCnt.getOrDefault(posNum, 0) + 1);
            int x = p[0], y = p[1];
            rowDir.put(x, rowDir.getOrDefault(x, 0) + 1);
            colDir.put(y, colDir.getOrDefault(y, 0) + 1);
            leftDir.put(x - y, leftDir.getOrDefault(x - y, 0) + 1);
            rightDir.put(y + x, rightDir.getOrDefault(y + x, 0) + 1);
        }

        int[][] dir = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {-1, -1}, {-1, 1}, {1, -1}, {0, 0},
        };
        for (int i = 0; i < cnt; i++) {
            int x = queries[i][0], y = queries[i][1];
            if (rowDir.containsKey(x) || colDir.containsKey(y) || leftDir.containsKey(x - y) || rightDir.containsKey(x + y)) {
                ans[i] = 1;
            }

            for (int[] mv : dir) {
                int nx = x + mv[0], ny = y + mv[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                int posNum = nx * n + ny;
                if (posCnt.containsKey(posNum) && posCnt.getOrDefault(posNum, 0) > 0) {
                    posCnt.put(posNum, 0);
                    if (rowDir.containsKey(nx)) {
                        if (rowDir.get(nx) == 1) {
                            rowDir.remove(nx);
                        } else {
                            rowDir.put(nx, rowDir.get(nx) - 1);
                        }
                    }
                    if (colDir.containsKey(ny)) {
                        if (colDir.get(ny) == 1) {
                            colDir.remove(ny);
                        } else {
                            colDir.put(ny, colDir.get(ny) - 1);
                        }
                    }

                    if (leftDir.containsKey(nx - ny)) {
                        if (leftDir.get(nx - ny) == 1) {
                            leftDir.remove(nx - ny);
                        } else {
                            leftDir.put(nx - ny, leftDir.get(nx - ny) - 1);
                        }
                    }

                    if (rightDir.containsKey(ny + nx)) {
                        if (rightDir.get(ny + nx) == 1) {
                            rightDir.remove(ny + nx);
                        } else {
                            rightDir.put(ny + nx, rightDir.get(ny + nx) - 1);
                        }
                    }

                }
            }

        }

        return ans;
    }


    public int[] gridIllumination2(int n, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> row = new HashMap<Integer, Integer>();
        Map<Integer, Integer> col = new HashMap<Integer, Integer>();
        Map<Integer, Integer> diagonal = new HashMap<Integer, Integer>();
        Map<Integer, Integer> antiDiagonal = new HashMap<Integer, Integer>();
        Set<Long> points = new HashSet<Long>();
        for (int[] lamp : lamps) {
            if (!points.add(hash(lamp[0], lamp[1]))) {
                continue;
            }
            row.put(lamp[0], row.getOrDefault(lamp[0], 0) + 1);
            col.put(lamp[1], col.getOrDefault(lamp[1], 0) + 1);
            diagonal.put(lamp[0] - lamp[1], diagonal.getOrDefault(lamp[0] - lamp[1], 0) + 1);
            antiDiagonal.put(lamp[0] + lamp[1], antiDiagonal.getOrDefault(lamp[0] + lamp[1], 0) + 1);
        }
        int[] ret = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int r = queries[i][0], c = queries[i][1];
            if (row.getOrDefault(r, 0) > 0) {
                ret[i] = 1;
            } else if (col.getOrDefault(c, 0) > 0) {
                ret[i] = 1;
            } else if (diagonal.getOrDefault(r - c, 0) > 0) {
                ret[i] = 1;
            } else if (antiDiagonal.getOrDefault(r + c, 0) > 0) {
                ret[i] = 1;
            }
            for (int x = r - 1; x <= r + 1; x++) {
                for (int y = c - 1; y <= c + 1; y++) {
                    if (x < 0 || y < 0 || x >= n || y >= n) {
                        continue;
                    }
                    if (points.remove(hash(x, y))) {
                        // 这种写法：上面的判断方式，必须要求 大于 0
                        row.put(x, row.get(x) - 1);
                        if (row.get(x) == 0) {
                            row.remove(x);
                        }
                        col.put(y, col.get(y) - 1);
                        if (col.get(y) == 0) {
                            col.remove(y);
                        }
                        diagonal.put(x - y, diagonal.get(x - y) - 1);
                        if (diagonal.get(x - y) == 0) {
                            diagonal.remove(x - y);
                        }
                        antiDiagonal.put(x + y, antiDiagonal.get(x + y) - 1);
                        if (antiDiagonal.get(x + y) == 0) {
                            antiDiagonal.remove(x + y);
                        }
                    }
                }
            }
        }
        return ret;
    }

    public long hash(int x, int y) {
        return (long) x + ((long) y << 32);
    }

    public static void main(String[] args) {
        gridIllumination q = new gridIllumination();
//        int n = 6;
//        int n = 4;
        int n = 1;
        int[][] grid = {
//            {2, 5}, {4, 2}, {0, 3}, {0, 5}, {1, 4}, {4, 2}, {3, 3}, {1, 0}
//            {2, 0}, {1, 2},
                {0, 0}, {0, 0},
        };
        int[][] query = {
//            {4, 3}, {3, 1}, {5, 3}, {0, 5}, {4, 4}, {3, 3}
//            {2, 3}, {0, 3}
                {0, 0}, {0, 0},

        };
        q.gridIllumination(n, grid, query);
    }
}
