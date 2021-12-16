package org.example.com.leetcode.other;

import java.util.Stack;

/**
 * 1003. 检查替换后的词是否有效
 * https://leetcode-cn.com/problems/check-if-word-is-valid-after-substitutions/
 *
 * 题解：
 *
 */
public class Q13 {
    public boolean isValid(String s) {
        boolean res = false;
        char[] chars = s.toCharArray();

        Stack<Character> st = new Stack<>();

        for (char c: chars) {
            if (c == 'c') {
                if (!st.empty() && st.peek() == 'b') {
                    st.pop();
                    if (!st.empty() && st.peek() == 'a') {
                        st.pop();
                        continue;
                    }
                }
                return res;
            } else {
                st.push(c);
            }
        }
        if (st.empty()) {
            res = true;
        }

        return res;
    }
}
