package com.benny.learning.algorithm.leetcode.dp.simple;

import java.util.Arrays;

/**
 * 473. 火柴拼正方形
 * https://leetcode-cn.com/problems/matchsticks-to-square/
 */
public class Q7 {
    // TODO 考虑使用Map来统计是否已经被使用？
    private boolean[] visited;
    private int width;

    public boolean makesquare(int[] matchsticks) {
        int len = matchsticks.length;
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0 || len < 4) {
            return false;
        }
        width = sum / 4;
        // 降序排序更好
        Arrays.sort(matchsticks);
        if (matchsticks[len - 1] > width) {
            return false;
        }
        visited = new boolean[len];
        int[] side = new int[4];    // 只要确保前三个满足条件，不需要判断第四条边的构成
        boolean res = dfs(matchsticks, 0, side, 0);
        return res;
    }

    private boolean dfs(int[] matchsticks, int index, int[] side, int sideNum) {
        if (side[0] == width
                && side[1] == width
                && side[2] == width
        ) {
            // 可以使用 sideNum > 3 代替
            return true;
        }
        // 任意一条边大于width都不需要继续探讨
        if (side[0] > width
                || side[1] > width
                || side[2] > width
        ) {
            return false;
        }
        if (index >= matchsticks.length) {
            return false;
        }
        for (int i = 0; i < matchsticks.length; i++) {
            if (visited[i]) {
                continue;
            }
            int cur = matchsticks[i];
            if (side[sideNum] == width) { // 处理下一条边线
                if (side[sideNum + 1] + cur > width) {  // 后续的值不需要测试
                    break;
                }
                visited[i] = true;
                side[sideNum + 1] += cur;
                if (dfs(matchsticks, index + 1, side, sideNum + 1)) {
                    return true;
                }
                side[sideNum + 1] -= cur;
                visited[i] = false;
            } else {    // 小于width
                if (side[sideNum] + cur > width) {  // 后续的值不需要测试
                    break;
                }
                visited[i] = true;
                side[sideNum] += cur;
                if (dfs(matchsticks, index + 1, side, sideNum)) {
                    return true;
                }
                side[sideNum] -= cur;
                visited[i] = false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Q7 q = new Q7();
        int[] nums = new int[]{
//                1, 1, 2, 2, 2
                5, 5, 5, 5, 16, 4, 4, 4, 4, 4, 3, 3, 3, 3, 4
        };
        q.makesquare(nums);
    }
}


