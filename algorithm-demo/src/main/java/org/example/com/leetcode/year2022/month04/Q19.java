package org.example.com.leetcode.year2022.month04;

import java.util.*;

/**
 * 821.字符的最短距离
 */
public class Q19 {
    // 利用栈结果处理
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];

        Deque<Integer> sk = new ArrayDeque<>();

        int pre = -100000;
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (cur != c) {
                sk.push(i);
            } else {
                while (!sk.isEmpty()) {
                    int index = sk.pop();
                    ans[index] = Math.min(index - pre, i - index);
                }
                pre = i;
            }
        }

        // 处理末尾不是c字符的情况
        while (!sk.isEmpty()) {
            int index = sk.pop();
            ans[index] = index - pre;
        }

        return ans;
    }

    public int[] shortestToChar2(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int cur = s.charAt(i);
            if (cur == c) {
                ans[i] = 0;
                continue;
            }

            // 往两侧遍历，直到遇到第一个c字符
            int left = i, right = i;
            // 一定会遍历到c，所有直接死循环，通过break中断
            while (true) {
                left = left - 1 >= 0 ? left - 1 : left;
                right = right + 1 < n ? right + 1 : right;
                if (s.charAt(left) == c) {
                    ans[i] = i - left;
                    break;
                }

                if (s.charAt(right) == c) {
                    ans[i] = right - i;
                    break;
                }
            }
        }

        return ans;
    }

    /**
     * 一维
     * 多源最短路径
     */
    public int[] shortestToChar3(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                ans[i] = 0;
                deque.add(i);
            }
        }

        while (!deque.isEmpty()) {
            int size = deque.size();

            for (int i = 0; i < size; i++) {
                int index = deque.pollFirst();
                if (index - 1 >= 0 && ans[index - 1] == -1) {
                    ans[index - 1] = ans[index] + 1;
                    deque.addLast(index - 1);
                }

                if (index + 1 < n && ans[index + 1] == -1) {
                    ans[index + 1] = ans[index] + 1;
                    deque.addLast(index + 1);
                }
            }
        }

        return ans;
    }


    /**
     * 两次遍历
     */
    public int[] shortestToChar4(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];

        int pre = -10005;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                pre = i;
            }
            ans[i] = i - pre;
        }

        int bac = 20010;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                bac = i;
            }
            ans[i] = Math.min(ans[i], bac - i);
        }


        return ans;
    }

}
