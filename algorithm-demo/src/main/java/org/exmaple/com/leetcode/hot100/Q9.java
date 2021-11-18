package org.exmaple.com.leetcode.hot100;

import java.util.Stack;

/**
 * 394. 字符串解码
 * https://leetcode-cn.com/problems/decode-string/
 */
public class Q9 {
    // TODO 优化效率
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == ']') {
                String temp = "";
                while (stack.peek() != '[') {
                    temp = stack.pop() + temp;
                }
                stack.pop();
                String numStr = "";
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    numStr = stack.pop() + numStr;
                }
                int num = Integer.parseInt(numStr);
                for (int i = 0; i < num; i++) {
                    for (char c2 : temp.toCharArray()) {
                        stack.add(c2);
                    }
                }
            } else {
                stack.add(c);
            }
        }

        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }

        return res;
    }

    public static void main(String[] args) {
        Q9 q = new Q9();
//        q.decodeString("3[a]2[bc]");
        q.decodeString("100[leetcode]");
    }
}


