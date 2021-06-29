package com.benny.learning.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 438. 找到字符串中所有字母异位词
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 */
public class Q4_1 {
    // 滑动窗口
    // 字母易位词，长度要保持一致，字母类型与数量均要保持一致
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> oriMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            oriMap.put(c, oriMap.getOrDefault(c, 0) + 1);
        }

        List<Integer> ans = new ArrayList<>();

        int len = s.length();
        int right = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (left < len) {
            char startC = s.charAt(left);
            if (!oriMap.containsKey(startC)) {
                left ++;
                continue;
            }
            map.put(startC, map.getOrDefault(startC, 0) + 1);
            right = left + 1;
            while (right < len
                    && oriMap.containsKey(s.charAt(right))
                    && !check(oriMap, map)) {
                char nextC = s.charAt(right);
                map.put(nextC, map.getOrDefault(nextC, 0) + 1);
                right++;
            }
            if (check(oriMap, map)) {
                ans.add(left);
            }
            map.put(startC, map.getOrDefault(startC, 0) - 1);
            left ++;
        }

        return ans;
    }

    private boolean check(Map<Character, Integer> ori, Map<Character, Integer> map) {
        if (map.size() != ori.size()) {
            return false;
        }
        for (char c : ori.keySet()) {
            if (!map.containsKey(c)) {
                return false;
            }
            if (map.get(c) != ori.get(c)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Q4_1 q = new Q4_1();
        q.findAnagrams("cbaebabacd", "abc");
    }
}


