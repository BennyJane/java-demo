package org.example.com.leetcode.dp.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 * https://leetcode-cn.com/problems/palindrome-partitioning/
 */
public class Q3 {
    // 回溯 + 记忆化搜索

    int[][] memo;
    List<List<String>> ret = new ArrayList<>();
    List<String> ans = new ArrayList<>();
    int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        memo = new int[n][n];

        dfs(s, 0);

        return ret;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            // 必须重新生成
            ret.add(new ArrayList<>(ans));
            return;
        }
        for (int j = i; j < n; j++) {
            // 检测是否是回文
            if (isPalindrome(s, i, j) == 1) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    // 记忆化搜索中，f[i][j] = 0 表示未搜索，1 表示是回文串，-1 表示不是回文串
    public int isPalindrome(String s, int i, int j) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (i >= j) {
            memo[i][j] = 1;
        } else if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = isPalindrome(s, i + 1, j - 1);
        } else {
            memo[i][j] = -1;
        }
        return memo[i][j];
    }
}


