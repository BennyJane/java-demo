package benny.jane.com.leetcode.dp.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 150. 逆波兰表达式求值
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
public class Q4_1 {
    public int evalRPN(String[] tokens) {
        if (tokens.length <= 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        // FIXME 注意 减法 与 除法 的相对顺序
        for (String s : tokens) {
            Integer newNum = 0;
            if (s.equals("+")) {
                int one = stack.pop();
                int two = stack.pop();
                newNum = one + two;
            } else if (s.equals("-")) {
                int one = stack.pop();
                int two = stack.pop();
                newNum = one - two;
            } else if (s.equals("*")) {
                int one = stack.pop();
                int two = stack.pop();
                newNum = one * two;
            } else if (s.equals("/")) {
                int one = stack.pop();
                int two = stack.pop();
                newNum = two / one;
            } else {
                newNum = Integer.parseInt(s);
            }

            stack.add(newNum);
        }

        return stack.peek();
    }

    public static void main(String[] args) {
        Q4_1 q = new Q4_1();
        String[] arr = new String[]{"4", "13", "5", "/", "+"};
        q.evalRPN(arr);
    }
}


