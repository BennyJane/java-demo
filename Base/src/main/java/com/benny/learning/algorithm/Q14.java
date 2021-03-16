package com.benny.learning.algorithm;

import java.util.List;
import java.util.Stack;

/**
 * 636. 函数的独占时间
 * https://leetcode-cn.com/problems/exclusive-time-of-functions/
 * 题解：
 * https://leetcode-cn.com/problems/exclusive-time-of-functions/solution/han-shu-de-du-zhan-shi-jian-by-leetcode/
 */
public class Q14 {
    class StockSpanner {
        Stack<Integer> prices, weights;

        public StockSpanner() {
            prices = new Stack<>();
            weights = new Stack<>();
        }

        public int next(int price) {
            int w = 1;
            while (!prices.isEmpty() && prices.peek() <= price) {
                prices.pop();
                w += weights.pop();
            }

            prices.push(price);
            weights.push(w);
            return w;
        }
    }
}
