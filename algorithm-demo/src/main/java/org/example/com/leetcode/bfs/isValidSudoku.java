package org.example.com.leetcode.bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class isValidSudoku {
    // 记录每行已经使用的数字
    List<Set<Character>> rows = new ArrayList<>();
    // 记录每列已经使用的数字
    List<Set<Character>> columns = new ArrayList<>();
    // 记录每个小方块内已经使用的数字
    List<Set<Character>> child = new ArrayList<>();
    int width = 9, w = 3;

    List<int[]> postions = new ArrayList<>();

    volatile boolean isEnd = false;
    char[] base = new char[]{
            '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    public boolean isValidSudoku(char[][] board) {
        // 参考 八皇后 算法
        for (int i = 0; i < width; i++) {
            columns.add(new HashSet<>());
            child.add(new HashSet<>());
            rows.add(new HashSet<>());
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                char cur = board[i][j];
                int childIndex = i / w * w + j / w;
                if (cur != '.') {
                    if (rows.get(i).contains(cur)
                            || columns.get(j).contains(cur) ||
                            child.get(childIndex).contains(cur)) {
                        System.out.println("0000");
                        return false;
                    }
                    rows.get(i).add(cur);
                    columns.get(j).add(cur);
                    child.get(childIndex).add(cur);

                } else {
                    postions.add(new int[]{i, j});
                }
            }
        }
        return true;
    }

}
