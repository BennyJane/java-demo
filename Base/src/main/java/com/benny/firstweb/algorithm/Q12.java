package com.benny.firstweb.algorithm;

import java.util.Stack;

/**
 * 1190. 反转每对括号间的字串
 * https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 * <p>
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * <p>
 * 注意，您的结果中 不应 包含任何括号。
 * <p>
 * 参考：Q7 写法
 */
public class Q12 {
    public String reverseParentheses(String s) {
        char[] chars = s.toCharArray();

        Stack<java.lang.String> st = new Stack<>();

        StringBuilder currentStr = new StringBuilder();

        for (char c : chars) {
            if (c == '(') {
                st.push(currentStr.toString());
                currentStr = new StringBuilder();
            } else if (c == ')') {
                StringBuilder temp = new StringBuilder();
                // 将currentStr中字符串反转顺序
                int length = currentStr.length();
                for (int i = length - 1; i >= 0; i--) {
                    temp.append(currentStr.charAt(i));
                }
                currentStr = new StringBuilder(st.pop() + temp.toString());
            } else {
                currentStr.append(c);
            }
        }

        return currentStr.toString();
    }
}
