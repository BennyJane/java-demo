package org.exmaple.com.leetcode.dp.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 241. 为运算表达式设计优先级
 * https://leetcode-cn.com/problems/different-ways-to-add-parentheses/
 */
public class Q4 {
    // 找出所有运行的运算顺序，不可重复
    // 分治算法：
    public List<Integer> diffWaysToCompute(String input) {
        if (input == null || input.length() <= 0) {
            return new ArrayList<Integer>();
        }

        ArrayList<Integer> curRes = new ArrayList<Integer>();
        int length = input.length();
        char[] charArray = input.toCharArray();
        for (int i = 0; i < length; i++) {  // 将逐个运算符号作为最后一次运算来使用
            char aChar = charArray[i];
            if (aChar == '+' || aChar == '-' || aChar == '*') { // 当前字符为 操作符、
                // TODO 递归
                List<Integer> leftList = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightList = diffWaysToCompute(input.substring(i + 1));
                for (int leftNum : leftList) {
                    for (int rightNum : rightList) {
                        if (aChar == '+') {
                            curRes.add(leftNum + rightNum);
                        } else if (aChar == '-') {
                            curRes.add(leftNum - rightNum);
                        } else {
                            curRes.add(leftNum * rightNum);
                        }
                    }
                }

            }
        }

        //  处理单字符的情况：不包含运算符号
        if (curRes.isEmpty()) {
            curRes.add(Integer.valueOf(input));
        }
        return curRes;
    }

}


