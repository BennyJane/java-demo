package org.example.com.leetcode.year2022.month04;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//819. 最常见的单词
public class Q17 {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        for (String c : banned) {
            set.add(c);
        }
        Map<String, Integer> map = new HashMap<>();
        String s = paragraph.toLowerCase();
        String ans = "";
        int maxCnt = 0;
        for (String c : s.split("\\?|'|;|!|\\.|,| ")) {
            if (set.contains(c) || c.trim().length() == 0) {
                continue;
            }
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > maxCnt) {
                maxCnt = map.get(c);
                ans = c;
            }
        }

        return ans;
    }

    public String mostCommonWord2(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<String>();
        for (String word : banned) {
            bannedSet.add(word);
        }
        int maxFrequency = 0;
        Map<String, Integer> frequencies = new HashMap<String, Integer>();
        StringBuffer sb = new StringBuffer();
        // 索引取到 n, 处理最后一个字符
        int length = paragraph.length();
        for (int i = 0; i <= length; i++) {
            if (i < length && Character.isLetter(paragraph.charAt(i))) {
                sb.append(Character.toLowerCase(paragraph.charAt(i)));
            } else if (sb.length() > 0) {
                String word = sb.toString();
                if (!bannedSet.contains(word)) {
                    int frequency = frequencies.getOrDefault(word, 0) + 1;
                    frequencies.put(word, frequency);
                    maxFrequency = Math.max(maxFrequency, frequency);
                }
                sb.setLength(0);
            }
        }
        String mostCommon = "";
        Set<Map.Entry<String, Integer>> entries = frequencies.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String word = entry.getKey();
            int frequency = entry.getValue();
            if (frequency == maxFrequency) {
                mostCommon = word;
                break;
            }
        }
        return mostCommon;
    }

}
