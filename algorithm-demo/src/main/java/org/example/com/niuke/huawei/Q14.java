package org.example.com.niuke.huawei;

import java.util.Scanner;

/**
 * HJ65 找两个字符串a,b中的最长公共子串
 */
public class Q14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String S1 = sc.next();
            String S2 = sc.next();
            if (S1.length() > S2.length()) {
                String tmp = S1;
                S1 = S2;
                S2 = tmp;
            }
            int m = S1.length(), n = S2.length();

            int[][] dp = new int[m + 1][n + 1];

            int maxLen = 0;
            String ans = "";

            for (int i = 1; i < m + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    // TODO 只处理相等的情况
                    if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    // 不相等，以i,j结尾的两个字符串一定不相等
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        ans = S1.substring(i - maxLen, i);
                    }
                }
            }

            System.out.println(ans);
        }
    }
}


class Main10{
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String s1=sc.nextLine();
            String s2=sc.nextLine();
            longString(s1,s2);
        }
    }
    public static void longString(String s1,String s2){
        String shortStr = s1.length() < s2.length() ? s1 : s2;
        String longStr = s1.length() > s2.length() ? s1 : s2;
        int shortLen = shortStr.length();
        int longLen = longStr.length();
        int maxLen = 0, start = 0;
        for(int i = 0; i < shortLen;i++) {
            // 剪枝，子串长度已经不可能超过maxLen，退出循环
            if(shortLen - i + 1 <= maxLen) {
                break;
            }
            // 左指针j，右指针k, 右指针逐渐向左逼近
            for(int j = i, k = shortLen; k > j; k--) {
                String subStr = shortStr.substring(i, k);
                if(longStr.contains(subStr) && maxLen < subStr.length()) {
                    maxLen = subStr.length();
                    start = j;
                    // 找到就立即返回
                    break;
                }
            }
        }
        System.out.println(shortStr.substring(start, start + maxLen));
    }
}


/**
 * HJ65 找两个字符串a,b中的最长公共子串
 */
class Main11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str1 = sc.next();
            String str2 = sc.next();
            //保证第一个参数为较短串
            if(str1.length()<str2.length()){
                System.out.println(test(str1,str2));
            }else {
                System.out.println(test(str2,str1));
            }

        }
    }

    //求最长公共子串,str1为较短串
    public static String test(String str1, String str2) {
        int len = str1.length();//记录最长公共子串的长度
        for (int i = 0; i < str1.length(); i++) {
            if(len == 0){ //说明没有公共子串
                break;
            }
            if (i + len <= str1.length()) { //在str1中截取长度len的子串，如果是str2的子串则返回
                String s = str1.substring(i, i + len);
                if (str2.contains(s)) {
                    return s;
                }
            } else {
                len--;
                i = -1;
            }
        }
        return null;
    }

}