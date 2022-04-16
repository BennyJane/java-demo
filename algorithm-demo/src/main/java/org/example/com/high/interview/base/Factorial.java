package org.example.com.high.interview.base;

import java.util.Arrays;

//https://leetcode-cn.com/problems/fibonacci-number/solution/fei-bo-na-qi-shu-by-leetcode-solution-o4ze/
public class Factorial {
    /**
     * n 取值范围[1, 30], 数字过大，陷入死循环，例如： factorial(50)，改成long，耗时非常长
     * 入参为50
     * 一般递归: 非常耗时, 递归树节点数为 2^^n
     * 记忆化递归：快， 时间复杂度O(n), 空间复杂度为O(n)
     * 尾递归：快， 空间复杂度为O(1)
     * 动态规划：快, 时间复杂度O(n), O(1)
     * 公式：快
     * 矩阵快速幂：log(n), 1
     */
    public static long factorial(long num) {
        if (num == 0 || num == 1) {
            // 前两个数都是1
            return num;
        }
        return factorial(num - 1) + factorial(num - 2);
    }

    // FIXME 记忆化递归搜索，快
    public static long factorial1(int n) {
        long[] cache = new long[n];
        Arrays.fill(cache, -1L);
        cache[0] = cache[1] = 1L;
        return dfs(n, cache);
    }

    public static long dfs(int n, long[] cache) {
        if (cache[n - 1] != -1L) {
            return cache[n - 1];
        }
        long res = dfs(n - 1, cache) + dfs(n - 2, cache);
        cache[n - 1] = res;
        return res;
    }

    public static long tailFactorial(long n, long first, long second) {
        if (n == 0) return first;
        return tailFactorial(n - 1, second, first + second);
    }

    public static long loopFactorial(long n) {
        if (n < 2) return 1L;
        long first = 1L, second = 1L;

        long ans = 0L;
        for (int i = 2; i < n; i++) {
            ans = first + second;
            first = second;
            second = ans;
        }

        return ans;
    }

    public static long mathFactorial(long n) {
        double sqrt5 = Math.sqrt(5);
        double fibN = Math.pow((1 + sqrt5) / 2, n) - Math.pow((1 - sqrt5) / 2, n);
        return Math.round(fibN / sqrt5);
    }

    public static void main(String[] args) {
        int n = 80;
//        System.out.println(factorial(n));
        System.out.println(factorial1(n));
        System.out.println(tailFactorial(n, 0, 1));
        System.out.println(loopFactorial(n));
        System.out.println(mathFactorial(n));
    }
}
