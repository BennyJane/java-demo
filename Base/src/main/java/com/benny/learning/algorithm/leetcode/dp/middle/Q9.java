package com.benny.learning.algorithm.leetcode.dp.middle;

import java.util.Arrays;

/**
 * 1048. 最长字符串链
 * https://leetcode-cn.com/problems/longest-string-chain/
 */
public class Q9 {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (m, n) -> m.length() - n.length());
        int len = words.length;
        // dp[i]: 表示索引i作为右边界时，最长链长度
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int ans = 1;
        for (int i = 1; i < len; i++) {
            String cur = words[i];
            int curL = cur.length();
            int maxLen = 1;
            for (int j = i - 1; j >= 0; j--) {
                String pre = words[j];
                if (curL - pre.length() > 1) {
                    break;
                }
                if (check(cur, pre)) {
                    maxLen = Math.max(maxLen, dp[j] + 1);
                }
            }
            dp[i] = maxLen;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 检测两个字符串是否相差一个字符
     *
     * @param a 较长的字符串
     * @param b
     * @return
     */
    private boolean check(String a, String b) {
        if (a.length() - b.length() != 1) {
            return false;
        }
        int index1 = 0;
        int index2 = 0;
        int count = 0;
        while (index1 < a.length() && index2 < b.length()) {
            if (a.charAt(index1) == b.charAt(index2)) {
                index1++;
                index2++;
                continue;
            } else {
                count++;
                index1++;
            }
        }
        // 考虑多出的字符位于末尾
        return count <= 1;

    }

    public static void main(String[] args) {
        Q9 q = new Q9();
        String[] words = new String[]{
//                "a", "b", "ba", "bca", "bda", "bdca"
//                "xbc", "pcxbcf", "xb", "cxbc", "pcxbc"
//                "abcd","dbqca"
                "xbc", "pcxbcf", "xb", "cxbc", "pcxbc"
//                "a", "b", "ab", "bac"
//                "a", "ab", "ac", "bd", "abc", "abd", "abdd"
        };
        q.longestStrChain(words);
    }
}



