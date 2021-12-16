package org.example.com.leetcode.array.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 500. 键盘行
 * https://leetcode-cn.com/problems/keyboard-row/
 */
public class Q38 {
    public String[] findWords(String[] words) {
        String s1 = "qwertyuiop";
        String s2 = "asdfghjkl";
        String s3 = "zxcvbnm";
        List<String> res = new ArrayList<>();
        int n1 = 0;
        int n2 = 0;
        int n3 = 0;
        for (String word : words) {
            String temp = word;
            word = word.toLowerCase();
            for (int i = 0; i < word.length(); i++) {
                if (s1.contains(word.charAt(i) + "")) n1++;
                if (s2.contains(word.charAt(i) + "")) n2++;
                if (s3.contains(word.charAt(i) + "")) n3++;
            }
            if (n1 == word.length() || n2 == word.length() || n3 == word.length()) {
                res.add(temp);
            }
            n1 = 0; n2 = 0; n3 = 0;
        }
        return res.toArray(new String[0]);
    }
}


