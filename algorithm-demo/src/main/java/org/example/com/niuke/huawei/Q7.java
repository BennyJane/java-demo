package org.example.com.niuke.huawei;


import java.util.Scanner;

/**
 * // HJ31 密码截取 --> 子字符串必须连续
 * 最长回文字符串: 不要求子字符串连续
 * <p>
 * 暴力求解： 先找出所有子序列，然后判断是否存在满足回文条件
 */
public class Q7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            int n = s.length();

            int[][] dp = new int[n][n];

            int ans = 1;
            for (int i = n - 1; i >= 0; i--) {
                dp[i][i] = 1;
                for (int j = i + 1; j < n; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        //  确保内部区间上也是回文
                        if (j- 1 > i +1 && dp[i+1][j -1] != (j -2 -i+1)) {
                            continue;
                        }
                        dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }

            System.out.println(ans);
        }
    }
}
