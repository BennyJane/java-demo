package org.example.com.leetcode.bfs;

import java.util.*;

public class solveSudoku {

    // 记录每行已经使用的数字
    List<Set<Character>> rows = new ArrayList<>();
    // 记录每列已经使用的数字
    List<Set<Character>> columns = new ArrayList<>();
    // 记录每个小方块内已经使用的数字
    List<Set<Character>> child = new ArrayList<>();
    int width = 9, w = 3;

    List<int[]> postions = new ArrayList<>();

    volatile boolean isEnd = false;
    char[] base = new char[] {
            '1','2','3','4','5','6','7','8','9'
    };

    public void solveSudoku(char[][] board) {
        // 参考 八皇后 算法
        for (int i = 0; i < width; i++) {
            columns.add(new HashSet<>());
            child.add(new HashSet<>());
            rows.add(new HashSet<>());
        }

        char[][] grid = new char[width][width];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                char cur = board[i][j];
                if (cur != '.') {
                    rows.get(i).add(cur);
                    columns.get(j).add(cur);
                    int m = i / w * w;
                    int n = j / w;
                    child.get(m + n).add(cur);

                } else {
                    postions.add(new int[]{i, j});
                }
                grid[i][j] = cur;
            }
        }
        dfs(0, grid, board);
    }

    private void dfs(int index, char[][] grid, char[][] ori) {
        if (index >= postions.size()) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    ori[i][j] = grid[i][j];
                }
            }
            isEnd = true;
            return;
        }
        if (isEnd) {
            return;
        }

        int[] pos = postions.get(index);
        int r = pos[0], c = pos[1];
        int childIndex = r / w * w + c / w;
        for (int i = 0; i < width; i++) {
            if (isEnd) {
                return;
            }
            char cur = base[i];
            if (rows.get(r).contains(cur) || columns.get(c).contains(cur) || child.get(childIndex).contains(cur)) {
                continue;
            }
            grid[r][c] = cur;
            rows.get(r).add(cur);
            columns.get(c).add(cur);
            child.get(childIndex).add(cur);
            dfs(index + 1, grid, ori);

            rows.get(r).remove(cur);
            columns.get(c).remove(cur);
            child.get(childIndex).remove(cur);
            grid[r][c] = '.';
        }
    }


    public static void main(String[] args) {
        solveSudoku s = new solveSudoku();
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        s.solveSudoku(board);
    }
}
