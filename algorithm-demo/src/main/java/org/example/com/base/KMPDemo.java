package org.example.com.base;

/**
 * https://leetcode-cn.com/problems/implement-strstr/solution/shua-chuan-lc-shuang-bai-po-su-jie-fa-km-tb86/
 * KMP 算法
 */
public class KMPDemo {
    public int strStr(String s, String child) {
        if (child.isEmpty()) {
            return 0;
        }

        // n为原串，m为匹配串长度
        int n = s.length(), m = child.length();
        // 原字符串、匹配串都添加空格，使其下表从1开始
        s = " " + s;
        child = " " + child;

        char[] sArr = s.toCharArray();
        char[] cArr = child.toCharArray();

        // 构建next数组，数据长度为匹配串长度（next数组是和匹配串相关）
        int[] next = new int[m + 1];
        // 构造从i = 2 开始
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功，j = next[j]
            while (j > 0 && cArr[i] != cArr[j + 1]) {
                j = next[j];
            }
            // 匹配成功，先让j++
            if (cArr[i] == cArr[j + 1]) {
                j++;
            }
            // 更新next[i], 结束本次循环，i++
            next[i] = j;
        }

        // 匹配过程，i = 1，j = 0 开始，i 小于等于原串长度 【匹配 i 从 1 开始】
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j = next(j)
            while (j > 0 && sArr[i] != cArr[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++，结束本次循环后 i++
            if (sArr[i] == cArr[j + 1]) j++;
            // 整一段匹配成功，直接返回下标
            if (j == m) return i - m;
        }

        return -1;
    }
}
