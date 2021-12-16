package org.example.com.leetcode.dp.middle;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 576. 出界的路径数
 * https://leetcode-cn.com/problems/out-of-boundary-paths/
 */
public class Q8_1 {
    // 记忆化搜索 + DFS
    // TODO 提交报错：部分案例不能通过

    int mod = (int) 1e9 + 7;
    int rowNum, colNum, stepLimit;
    Long[][][] memo; // FIXME 需要特殊处理超出最大值的情况

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int limit = 51; // 根据题目信息，横纵坐标以及移动次数都小于等于50
        memo = new Long[limit][limit][limit];
        stepLimit = maxMove;
        rowNum = m;
        colNum = n;
        dfs(startRow, startColumn, 0);
        return (int) dfs(startRow, startColumn, 0);
    }

    private long dfs(int x, int y, int step) {
        if (step > stepLimit) {
            return 0;
        }
        if (x < 0 || y < 0 || x >= rowNum || y >= colNum) {
            return 1;
        }

        if (memo[x][y][step] != null) {
            return memo[x][y][step];
        }
        // 计算上、下、左、右各个方向寻找出界道路的次数，并保存状态
        // 每个计算的位置都需要考虑超出限制的情况
        long leftRes = dfs(x, y - 1, step + 1) % mod;
        long rightRes = dfs(x, y + 1, step + 1) % mod;
        long upRes = dfs(x - 1, y, step + 1) % mod;
        long downRes = dfs(x + 1, y, step + 1) % mod;
        memo[x][y][step] = (leftRes + rightRes + upRes + downRes) % mod;
        return memo[x][y][step];
    }

}



