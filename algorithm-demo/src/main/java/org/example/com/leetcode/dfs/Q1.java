package org.example.com.leetcode.dfs;

public class Q1 {
    /**
     * 嵌套循环法
     * 一旦确定前两个数，可以递推出后续所有数，只需要验证后续是否满足累加条件即可
     * <p>
     * 关键点：枚举前两个数的所有可能; 使用字符串加法，处理过大的整数输入导致的溢出
     */
    public boolean isAdditiveNumber(String num) {
        int n = num.length();

        // firstEnd: 第一数字的结尾索引，可以取到
        for (int firstEnd = 0; firstEnd < n - 1; firstEnd++) {
            // 以0开头，且长度大于1的数字，不满足条件，直接退出循环
            if (num.charAt(0) == '0' && firstEnd > 0) {
                break;
            }
            // secondEnd: 第二个数字的结尾索引
            for (int secondEnd = firstEnd + 1; secondEnd < n; secondEnd++) {
                if (num.charAt(firstEnd + 1) == '0' && secondEnd > firstEnd + 1) {
                    break;
                }
                // 已经确定前两个数字，验证后续是否满足累加序列
            }
        }


        return true;
    }

    public boolean valid(int secondStart, int secondEnd, String num) {
        return false;
    }
}
