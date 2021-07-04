package com.benny.learning.algorithm.leetcode.dp.simple;

import java.util.Stack;

/**
 * 224. 基本计算器
 * https://leetcode-cn.com/problems/basic-calculator/
 */
public class Q4_2 {
    // 注意点： 运算优先级(可以不考虑，因为只有加减法)；括号处理
    public int calculate(String s) {
        int len = s.length();
        if (len <= 0) {
            return 0;
        }
        int ans = 0;
        char[] arr = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int left = 0;
        while (left < len) {
            Character cur = s.charAt(left);
            
        }
        for (char c : arr) {
            Integer newNum = 0;
            if (c - ' ' == 0) {
                continue;
            }

            if (c == '+') {
                int one = stack.pop();
                int two = stack.pop();
                newNum = one + two;
            } else if (c == '-') {
                int one = stack.pop();
                int two = stack.pop();
                newNum = two - one;
            } else {
                newNum = c - '0';
            }
            stack.add(newNum);
        }

        return stack.peek();
    }

    public static void main(String[] args) {
        Q4_2 q = new Q4_2();
        String s = "1 + 1";
        q.calculate(s);
    }
}


