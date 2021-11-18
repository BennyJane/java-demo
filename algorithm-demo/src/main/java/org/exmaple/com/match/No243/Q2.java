package org.exmaple.com.match.No243;

/**
 * 1881. 插入后的最大值
 * https://leetcode-cn.com/problems/maximum-value-after-insertion/
 */
public class Q2 {
    public static void main(String[] args) {
        Q2 q = new Q2();
        q.maxValue("-13", 2);
    }

    public String maxValue(String n, int x) {
        boolean Ne = n.contains("-") ? true : false;
        String ori = Ne ? n.substring(1, n.length()) : n;

        int index = -1;
        for (int i = 0; i < ori.length(); i++) {
            String s = ori.substring(i, i + 1);
            int sNum = Integer.valueOf(s);
            if (Ne) {   // 负数
                if (sNum > x) {
                    index = i;
                    break;
                }
            } else {    // 正数
                if (sNum < x) {
                    index = i;
                    break;
                }
            }
        }

        String ans = "";
        if (index == -1) {
            ans = ori + x;
            ans = Ne ? "-" + ans : ans;
        } else {
            ans = ori.substring(0, index) + x + ori.substring(index, ori.length());
            ans = Ne ? "-" + ans : ans;
        }
        return ans;
    }
}


