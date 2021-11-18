package org.exmaple.com.leetcode;

public class Q5 {
    public String removeDuplicateLetters(String s) {
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        // 统计每个子母的数量
        for (int i=0 ; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }

        StringBuffer sb = new StringBuffer();
        char[] charList = s.toCharArray();
        for (char c : charList) {
            if (!vis[c - 'a']) {
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > c ){
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                vis[c - 'a'] = true;
                sb.append(c);
            }
            num[c - 'a']--; // 该字母剩余数量减一
        }

        return sb.toString();
    }
}
