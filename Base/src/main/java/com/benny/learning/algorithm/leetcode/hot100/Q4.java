package com.benny.learning.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 49. 字母异位词分组
 * https://leetcode-cn.com/problems/group-anagrams/
 */
public class Q4 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        int len = strs.length;
        boolean[] visited = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (visited[i]) {
                continue;
            }
            String s = strs[i];
            List<String> list = new ArrayList<>();
            list.add(s);
            visited[i] = true;
            for (int j = i + 1; j < len; j++) {
                if (visited[j]) {
                    continue;
                }
                String next = strs[j];
                if (check(s, next)) {
                    list.add(next);
                    visited[j] = true;
                }
            }
            ans.add(list);
        }
        return ans;
    }

    private boolean check(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] arr1 = s.toCharArray();
        Arrays.sort(arr1);
        char[] arr2 = t.toCharArray();
        Arrays.sort(arr2);
        for (int i = 0; i < s.length(); i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q4 q = new Q4();
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        q.groupAnagrams(strs);
    }
}


