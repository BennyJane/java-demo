package com.benny.learning.algorithm.leetcode.hot100;

import java.util.Arrays;

public class Q4_2 {
    public boolean checkInclusion(String s1, String s2) {
        char[] need = new char[26];
        char[] window = new char[26];
        for (char c : s1.toCharArray()) {
            need[c - 'a']++;
        }

        int left = 0;
        int right = 0;
        int len = s2.length();

        while (right < len) {
            window[s2.charAt(right) - 'a']++;
            while (right - left + 1 == s1.length()) {
                if (Arrays.equals(need, window)) {
                    return true;
                }
                window[s2.charAt(left) - 'a']--;
                left++;
            }
            right++;
        }
        return false;
    }
}


