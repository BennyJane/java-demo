package benny.jane.com.leetcode.other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 最大坡度
 */
public class Q8 {
    public static void main(String[] args) {
        String s = "";

    }

    static int wayFirst(int[] data) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < data.length; i++) {
            if (stack.isEmpty() || data[stack.peek()] > data[i]) {
                stack.push(i);
            }
        }
        for (int i = data.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && data[stack.peek()] <= data[i]) {
                int cur = stack.pop();
                res = Math.max(res, i - cur);
            }
        }
        return res;
    }
}
