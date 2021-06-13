package com.benny.learning.algorithm.match.No245;

/**
 * 5784. 重新分配字符使所有字符串都相等
 */
public class Q4 {
    public boolean makeEqual(String[] words) {
        int len = words.length;
        final int CHAR_NUM = 26;
        int[] count = new int[CHAR_NUM];
        for (String s : words) {
            for (char c : s.toCharArray()) {
                int index = c - 'a';
                count[index] += 1;
            }
        }
        for (int n : count) {
            if (n > 0 && n % len != 0) {
                return false;
            }
        }

        return true;
    }
}


