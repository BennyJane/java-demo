package org.exmaple.com.leetcode.hot100;

import com.benny.learning.algorithm.Q;

import java.util.HashMap;
import java.util.Map;

/**
 * 5. 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * <p>
 * TODO 较为典型，可以写题解
 */
public class Q2 {
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        char[] arr = s.toCharArray();
        // 统计每个字符最右侧的索引位置
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(arr[i], i);
        }

        String ans = "";

        for (int i = 0; i < s.length(); i++) {
            char cur = arr[i];
            int tailIndex = map.get(cur);
            if (i == tailIndex) {
                String str = s.substring(i, i + 1);
                if (str.length() > ans.length()) {
                    ans = str;
                }
            }
            for (int j = tailIndex; j > i; j--) {
                int end = j;
                int start = i;
                while (start <= end && arr[start] == arr[end]) {
                    start++;
                    end--;
                }
                if (start > end) {    // 判断是回文串
                    String str = s.substring(i, j + 1);
                    if (str.length() > ans.length()) {
                        ans = str;
                    }
                    break;
                }
            }
        }

        return ans;
    }

    // 优化
    public String longestPalindrome2(String s) {
        // 长度0 1的特例直接返回
        if (s.length() < 2) {
            return s;
        }
        char[] arr = s.toCharArray();
        // 统计每个字符的相同字符的最右侧索引位置
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(arr[i], i);
        }

        String ans = "";
        int maxLen = 0;

        // 逐个字符作为起始点
        for (int i = 0; i < s.length(); i++) {
            // 确定回文出现的范围[i, tail]
            char cur = arr[i];
            int tail = map.get(cur);
            // 剪枝： 减少不必要的循环
            if (tail - i + 1 <= maxLen) {
                continue;
            }
            for (int j = tail; j >= i; j--) {
                int end = j;
                int start = i;
                // 检验是否符合回文的定义
                while (start <= end && arr[start] == arr[end]) {
                    start++;
                    end--;
                }
                // start > end : 确保上述while检测了整个回文字符串
                // 只需要有一个满足的，就是i起点下的最长，退出for循环
                if (start > end) {    // 判断是回文串
                    if (j - i + 1 > maxLen) {
                        ans = s.substring(i, j + 1);
                        maxLen = j - i + 1;
                    }
                    break;
                }
            }
        }

        return ans;
    }

    // TODO 研究标准答案


    public String longestPalindrome3(String s) {
        String ans = "";
        int maxLen = 0;
        int len = s.length();
        // （len *2 -1） / 2: 偶数长度，中心位置左侧索引
        for (int i = 0; i < len * 2 - 1; i++) {
            int left = i / 2;
            int right = i / 2 + i % 2;
            int childLen = 0;   // 计算当前回文串长度
            while (left >= 0 && right < len
                    && s.charAt(left) == s.charAt(right)) {
                childLen += left == right ? 1 : 2;
                left--;
                right++;
            }
            if (childLen > maxLen) {
                maxLen = childLen;
                ans = s.substring(left++, right);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Q2 q = new Q2();
        String res;
//        res = q.longestPalindrome("babad");
//        res = q.longestPalindrome("aacabdkacaa");
        res = q.longestPalindrome2("aacabdkacaa");
//        res = q.longestPalindrome("cbbd");
        System.out.println(res);
    }
}


