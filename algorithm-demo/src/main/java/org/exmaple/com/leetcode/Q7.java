package org.exmaple.com.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 */
public class Q7 {
    public static void main(String[] args) {
        String s = "";

    }

    static void wayFirst(String s) {
        char[] chars = s.toCharArray();
        Deque<Integer> stackNum = new ArrayDeque<>();
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for (int i =0 ; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                num = num *10 + Integer.parseInt(chars[i] + "");
            } else if ( chars[i] == '[') {
                stackNum.addLast(num);
                stack.addLast(sb.toString());
                sb = new StringBuilder();
                num = 0;
            } else if (chars[i] == ']') {
                StringBuilder temp = new StringBuilder();
                int n = stackNum.removeLast();
                while (n > 0) {
                    temp.append(sb);
                    n--;
                }
                sb = new StringBuilder(stack.removeLast() + temp);
            } else {
                sb.append(chars[i]);
            }
        }
        System.out.println(sb.toString());

    }
}
