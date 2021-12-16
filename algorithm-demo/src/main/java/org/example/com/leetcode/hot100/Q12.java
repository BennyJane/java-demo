package org.example.com.leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * 72. 编辑距离
 * https://leetcode-cn.com/problems/edit-distance/
 */
public class Q12 {
    // 递归
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if (m * n == 0) {
            return Math.max(m, n);
        }

        if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
            return minDistance(word1.substring(0, m - 1),
                    word2.substring(0, n - 1));
        }

        int min = Math.min(
                minDistance(word1.substring(0, m - 1), word2.substring(0, n - 1)),
                minDistance(word1.substring(0, m), word2.substring(0, n - 1))
        );
        min = Math.min(min,
                minDistance(word1.substring(0, m - 1), word2.substring(0, n))
        );
        return min + 1;
    }

    // 记忆化

    Map<String, Integer> memo = new HashMap<>();
    // TODO 考虑使用int[][] dp 存储
    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if (m * n == 0) {
            return Math.max(m, n);
        }

        if (memo.containsKey(word1 + "_" + word2)) {
            return memo.get(word1 + "_" + word2);
        }

        if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
            return minDistance(word1.substring(0, m - 1),
                    word2.substring(0, n - 1));
        }

        int min = Math.min(
                minDistance(word1.substring(0, m - 1), word2.substring(0, n - 1)),
                minDistance(word1.substring(0, m), word2.substring(0, n - 1))
        );
        min = Math.min(min,
                minDistance(word1.substring(0, m - 1), word2.substring(0, n))
        );
        min++;
        memo.put(word1 + "_" + word2, min + 1);
        return min;
    }


    public static void main(String[] args) {
        Q12 q = new Q12();
        int res = q.minDistance2("horse", "ros");
        System.out.println(res);
    }
}


