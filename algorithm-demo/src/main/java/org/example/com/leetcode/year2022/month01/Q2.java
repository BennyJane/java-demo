package org.example.com.leetcode.year2022.month01;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 * https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/
 */
public class Q2 {
    public String findLongestWord(String s, List<String> dictionary) {
        Collections.sort(dictionary, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o2.length() - o1.length();
                } else {
                    return o1.compareTo(o2);
                }
            }
        });
        for (String target : dictionary) {
            int start = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == target.charAt(start)) {
                    start++;
                }
                if (start == target.length()) {
                    return target;
                }
            }
        }

        return "";
    }
}


