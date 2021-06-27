package com.benny.learning.algorithm.leetcode.hot100;

/**
 * 516. 最长回文子序列
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 */
public class Q2_3 {
    // 子序列字符的相对位置不变
    public int longestPalindromeSubseq(String s) {
        int ans = 0;
        int len = s.length();
        // （len *2 -1） / 2: 偶数长度，中心位置左侧索引
        for (int i = 0; i < len * 2 - 1; i++) {
            int left = i / 2;
            int right = i / 2 + i % 2;
            int childLen = 0;   // 计算当前回文串长度
            while (left >= 0 && right < len
                    && s.charAt(left) == s.charAt(right)) {
                childLen += left == right ? 1 : 2;
                left--;
                right++;
            }
            ans = Math.max(ans, childLen);
        }

        return ans;
    }
}


