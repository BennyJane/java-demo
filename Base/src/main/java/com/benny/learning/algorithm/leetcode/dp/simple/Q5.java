package com.benny.learning.algorithm.leetcode.dp.simple;

import com.benny.learning.algorithm.Q;

public class Q5 {
    private int ans = 0;
    private boolean[] visited;

    // 没有解决前缀0的情况：前缀0可以重复
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        visited = new boolean[10];
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                dfs(n, 1);
            } else {
                visited[i] = true;
                dfs(n, 1);
                visited[i] = false;
            }
        }
        return ans;
    }

    private void dfs(int len, int index) {
        if (index >= len) {
            ans++;
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            dfs(len, index + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Q5 q = new Q5();
        q.countNumbersWithUniqueDigits(3);
    }
}


