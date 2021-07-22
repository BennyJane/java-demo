package com.benny.learning.algorithm.leetcode.dp.middle;

import javafx.util.Pair;
import org.apache.tomcat.util.modeler.ParameterInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 1301. 最大得分的路径数目
 * https://leetcode-cn.com/problems/number-of-paths-with-max-score/
 */
public class Q11 {

    Pair<Integer, Integer>[][] dp;
    int mod = (int) 1e9 + 7;

    public int[] pathsWithMaxScore(List<String> board) {
        int m = board.size();
        // dp[x][y]: 表示坐标(x, y)位置处，取得最大得分以及数量
        dp = new Pair[m][m];
        dp[m - 1][m - 1] = new Pair<>(0, 1);
        dp[0][0] = new Pair<>(0, 1);
        //  初始化状态
        for (int i = m - 2; i >= 0; i--) {
            // 最后一行
            if (board.get(m - 1).charAt(i) == 'X' || dp[m - 1][i + 1].getKey() == -1) {
                dp[m - 1][i] = new Pair<>(-1, 1);
            } else {
                int next = Integer.parseInt(String.valueOf(board.get(m - 1).charAt(i)));
                dp[m - 1][i] = new Pair<>(dp[m - 1][i + 1].getKey() + next, 1);
            }

            // 最后一列
            if (board.get(i).charAt(m - 1) == 'X' || dp[i + 1][m - 1].getKey() == -1) {
                dp[i][m - 1] = new Pair<>(-1, 1);
            } else {
                int next = Integer.parseInt(String.valueOf(board.get(i).charAt(m - 1)));
                dp[i][m - 1] = new Pair<>(dp[i + 1][m - 1].getKey() + next, 1);
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                // 先判断当前位置类型
                if (board.get(i).charAt(j) == 'X') {
                    dp[i][j] = new Pair<>(-1, 1);
                    continue;
                }
                if (board.get(i).charAt(j) == 'E') {
                    int[] res = check(0, 0);
                    if (res[0] == -1) {
                        return new int[2];
                    }
                    return check(0, 0);
                }

                // 数值类型
                int curNum = Integer.parseInt(String.valueOf(board.get(i).charAt(j)));
                int[] res = check(i, j);
                if (res[0] == -1) {
                    dp[i][j] = new Pair<>(-1, 1);
                } else {
                    dp[i][j] = new Pair<>(curNum + res[0], res[1]);
                }
            }
        }

        return new int[2];
    }

    private int[] check(int x, int y) {
        int goal = -1;
        int count = 0;
        if (dp[x][y + 1].getKey() > dp[x + 1][y].getKey()) {
            goal = dp[x][y + 1].getKey();
            count = dp[x][y + 1].getValue();
        } else if (dp[x][y + 1].getKey() == dp[x + 1][y].getKey()) {
            goal = dp[x + 1][y].getKey();
            count = dp[x + 1][y].getValue() + dp[x][y + 1].getValue();
        } else {
            goal = dp[x + 1][y].getKey();
            count = dp[x + 1][y].getValue();
        }

        // 如果没有使用if else if 连续；一般要先比较等于的情况，再考虑不等的情况，
        if (dp[x + 1][y + 1].getKey() == goal) {
            count = dp[x + 1][y + 1].getValue() + count;
        }

        if (dp[x + 1][y + 1].getKey() > goal) {
            goal = dp[x + 1][y + 1].getKey();
            count = dp[x + 1][y + 1].getValue();
        }

        return new int[]{goal % mod, count % mod};
    }

    public static void main(String[] args) {
        Q11 q = new Q11();
        List<String> list = new ArrayList<>();
//        list.add("E23");
//        list.add("2X2");
//        list.add("12S");

//        list.add("E11");
//        list.add("XXX");
//        list.add("11S");

        list.add("EX");
        list.add("XS");
        q.pathsWithMaxScore(list);
    }
}


