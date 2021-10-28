package benny.jane.com.match.N259;

import java.util.LinkedList;
import java.util.Queue;

public class Q4 {
    public String longestSubsequenceRepeatedK(String s, int k) {
        int len = s.length();
        String ans = "";
        Queue<String> queue = new LinkedList<>();
        // 初始长度为0
        queue.add("");
        // 广度搜索优先
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String pre = queue.poll();  // 前缀
                // 讨论下一个字符的可能性
                for (int i = 0; i < 26; i++) {
                    String next = pre + (char) ('a' + i);
                    // 需要验证该前缀和是否可以重复k次
                    if (isKSub(s, next, k)) {
                        ans = next;
                        queue.add(next);
                    }
                }

            }
        }


        return ans;
    }

    // 题目： 判断字符串sub是否是s的子序列
    private boolean isKSub(String s, String sub, int k) {
        int len1 = s.length();
        String target = "";
        for (int i = 0; i < k; i++) {
            target += sub;
        }

        int len2 = target.length();
        int index = 0;
        for (int i = 0; i < len1; i++) {
            if (index >= len2) {
                break;
            }
            if (s.charAt(i) == target.charAt(index)) {
                index++;
            }
        }
        return index >= len2;
    }
}
