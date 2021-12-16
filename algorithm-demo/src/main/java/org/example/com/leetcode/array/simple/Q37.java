package org.example.com.leetcode.array.simple;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 739. 每日温度
 */
public class Q37 {
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            int cur = temperatures[i];
            int right = i;
            while (right < len && temperatures[right] <= cur) {
                right++;
            }
            if (right >= len) {
                ans[i] = 0;
            } else {
                ans[i] = right - i;
            }
        }

        return ans;
    }

    //维护一个存储下标的单调栈，从栈底到栈顶的下标对应的温度列表中的温度依次递减。
    // 如果一个下标在单调栈里，则表示尚未找到下一次温度更高的下标。
    public int[] dailyTemperatures2(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

}


