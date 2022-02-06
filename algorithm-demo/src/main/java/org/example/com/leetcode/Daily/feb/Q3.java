package org.example.com.leetcode.Daily.feb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//1414. 和为 K 的最少斐波那契数字数目
public class Q3 {
    // FIXME 超时
    public int findMinFibonacciNumbers1(int k) {
        // !! 斐波那锲数列长度有限
        List<Integer> array = new ArrayList<>();
        int a = 1, b = 1;
        array.add(a);
        array.add(b);
        int res = a + b;
        while (res <= k) {
            array.add(res);
            a = b;
            b = res;
            res = a + b;
        }

        int[] dp = new int[k + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i <= k; i++) {
            for (int x : array) {
                if (i >= x) {
                    dp[i] = Math.min(dp[i], dp[i - x] + 1);
                }
            }
        }

        return dp[k];
    }


    int minCnt = Integer.MAX_VALUE;

    public int findMinFibonacciNumbers(int k) {
        List<Integer> array = new ArrayList<>();
        int a = 1, b = 1;
        array.add(a);
        array.add(b);
        int res = a + b;
        while (res <= k) {
            array.add(res);
            if (res == k) {
                return 1;
            }
            a = b;
            b = res;
            res = a + b;
        }
        // 必须优先考虑使用较大的值
        Collections.sort(array, (m, n) -> n - m);
        dfs(array, 0, k, 0);
        return minCnt;
    }

    private void dfs(List<Integer> arr, int index, int rest, int cnt) {
        if (index >= arr.size()) {
            return;
        }
        if (rest == 0) {
            minCnt = Math.min(minCnt, cnt);
            return;
        }
        // TODO 找到第一个满足条件的解，就可以直接返回
        if (cnt >= minCnt || minCnt != Integer.MAX_VALUE) {
            return;
        }

        int cur = arr.get(index);
        int maxCount = rest / cur;
        // 考虑当前值取[0, maxCount]的情况
        for (int i = maxCount; i >= 0; i--) {
            dfs(arr, index + 1, rest - cur * i, cnt + i);
        }
    }

    // 贪心思想
    public int findMinFibonacciNumbers2(int k) {
        List<Integer> array = new ArrayList<>();
        int a = 1, b = 1;
        array.add(a);
        array.add(b);
        int res = a + b;
        while (res <= k) {
            array.add(res);
            if (res == k) {
                return 1;
            }
            a = b;
            b = res;
            res = a + b;
        }

        int n = array.size();
        int cnt = 0;
        for (int i = n; i >= 0; i--) {
            int cur = array.get(i);
            if (k >= cur) {
                int tmp = k / cur;
                cnt += tmp;
                k -= tmp * cur;
            }
            if (k == 0) return cnt;
        }

        return cnt;
    }

    public int findMinFibonacciNumbers3(int k) {
        int a = 1, b = 1;
        int res = a + b;
        while (res <= k) {
            a = b;
            b = res;
            res = a + b;
        }

        int cnt = 0;
        while (res > 0) {
            if (k >= res) {
                int tmp = k / res;
                cnt += tmp;
                k -= tmp * res;
            }
            if (k == 0) return cnt;
            // 斐波那契数列：逆向计算
            res = b;
            b = a;
            a = res - a;
        }

        return 0;
    }

    public static void main(String[] args) {
        Q3 q = new Q3();
        q.findMinFibonacciNumbers(6395645);
    }
}
