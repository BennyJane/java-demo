package org.exmaple.com.leetcode.other;

import java.util.*;
import java.util.function.Function;

/**
 * N 天后的牢房
 */
public class Q1 {
    public int[] prisonAfterNDays(int[] cells, int n) {
        Map<Integer, Integer> seen = new HashMap();

        int num = cells.length;

        // 使用单个数字：记录不同状态
        int state = 0;
        for (int i = 0; i < num; i++) {
            // 二进制数组 转 数值; 异或 只要有一个1，就为1
            // 也可以只处理cells[i] == 1的情况
            state ^= cells[i] << i;
        }

        // 计算下一个状态
        Function<Integer, Integer> func = (Integer ori) -> {
            int nextState = 0;  // 初始状态为0，只需要考虑为1的情况
            for (int i = 1; i < 7; i++) {
                // 计算左右位置上的值： 向右移动n位，使用&符号，取得原来的值
                int left = ori >> (i - 1) & 1;
                int right = ori >> (i + 1) & 1;
                if (left == right) {
                    nextState ^= 1 << i;
                }
            }
            return nextState;
        };

        while (n > 0) {
            if (seen.containsKey(state)) {
                // 计算循环间隔
                int last = seen.get(state);
                int interval = last - n;
                // 只在第一次进入时，有缩小n的作用，后续n不变，执行func，直到为0
                n %= interval;
            }

            seen.put(state, n); // 记录状态 对应的索引

            if (n > 0) {    // 计算下一个状态，并更新state
                n--;
                state = func.apply(state);
            }
        }

        // 复原state
        int[] ans = new int[num];
        for (int i = 0; i < num; i++) {
            ans[i] = (state >> i) & 1;
        }

        return ans;
    }

    // FIXME 存在错误：部分测试用例无法通过
    // 循环间隔 与 最后状态没有计算正确
    public int[] prisonAfterNDays2(int[] cells, int n) {
        HashSet<Integer> set = new HashSet<>();
        List<Integer> seen = new ArrayList<>();

        int LENGTH = 8;
        int state = 0;
        for (int i = 0; i < LENGTH; i++) {
            if (cells[i] > 0) {
                state ^= 1 << i;
            }
        }

        Function<Integer, Integer> func = ori -> {
            int nextState = 0;
            for (int i = 1; i < LENGTH - 1; i++) {
                int left = (ori >> (i - 1)) & 1;
                int right = (ori >> (i + 1)) & 1;
                if (left == right) {
                    nextState ^= 1 << i;
                }
            }
            return nextState;
        };

        while (n > 0) {
            if (set.contains(state)) {
                int gap = seen.size();
                n %= gap;
                System.out.println(n + " _ " + gap);
                state = seen.get(n);
                break;
            }
            seen.add(state);
            set.add(state);
            state = func.apply(state);
            n--;
        }

        int[] ans = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            ans[i] = (state >> i) & 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(0 << 2);
        System.out.println(1 << 2);
        System.out.println(0 ^ 1);
        System.out.println(0 | 1);
        System.out.println(0 & 1);
    }
}
