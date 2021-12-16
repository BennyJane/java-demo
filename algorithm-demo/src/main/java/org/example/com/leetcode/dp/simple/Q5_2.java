package org.example.com.leetcode.dp.simple;

public class Q5_2 {
    public int countNumbersWithUniqueDigits(int n) {
        //个位为0，
        if(n==0){
            return 1;
        }
        int res = 0;
        int[] dp = new int[n];
        //初始化
        dp[0] = 9;
        //分别计算 i位数 不包含0时对应的数量
        for (int i = 1; i <n ; i++) {
            dp[i] = dp[i-1]*(9-i);
        }
        //最终结果加上不包含0的情况
        for (int i = 0; i <n ; i++) {
            res += dp[i];
        }
        //最终结果加上包含0的情况
        for (int i = 2; i <=n ; i++) {
            res += dp[i-2]*(i-1);
        }
        //加上0这种特殊情况
        return res+1;
    }

    /**
     * 排列组合：n位有效数字 = 每一位都从 0~9 中选择，且不能以 0 开头
     * 1位数字：0~9                      10
     * 2位数字：C10-2，且第一位不能是0      9 * 9
     * 3位数字：C10-3，且第一位不能是0      9 * 9 * 8
     * 4位数字：C10-4，且第一位不能是0      9 * 9 * 8 * 7
     * ... ...
     * 最后，总数 = 所有 小于 n 的位数个数相加
     */
    public int countNumbersWithUniqueDigits2(int n) {
        if (n == 0) return 1;
        int first = 10, second = 9 * 9;
        int size = Math.min(n, 10);
        for (int i = 2; i <= size; i++) {
            first += second;
            second *= 10 - i;
        }
        return first;
    }


    public static void main(String[] args) {
        Q5_2 q = new Q5_2();
        q.countNumbersWithUniqueDigits(3);
    }
}


