package org.example.com.leetcode.other;

import java.util.Stack;

/**
 * 921. 使括号有效的最少添加
 * https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid/
 */
public class Q9 {
    public static void main(String[] args) {
        String s = "()))((";
        wayFirst(s);

    }

    static void wayFirst(String S) {
        Stack<Character> st = new Stack<>();
        char[] chars = S.toCharArray();
        int addNum = 0;
        for (char c : chars) {
            if (c == '(') {
                st.push(c);
            } else {
                if (st.empty()) {
                    addNum++;
                } else {
                    st.pop();
                }
            }
        }
        System.out.println(st);
        addNum = addNum + st.size();
        System.out.println("length: " + addNum);
    }
}
