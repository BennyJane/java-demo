package org.example.com.match.No298;

public class Q3_1 {
    public static void main(String[] args) {
        Q3_1 q = new Q3_1();
//        q.longestSubsequence("1001010", 5);
//        q.longestSubsequence("00101001", 1);
        q.longestSubsequence("001010101011010100010101101010010", 93951055);
    }

    public int longestSubsequence(String s, int k) {
        kStr = Integer.toBinaryString(k);
        char[] kArr = kStr.toCharArray();
        int m = kArr.length;
        kLen = m;

        char[] array = s.toCharArray();
        int n = array.length;
        if (n < m) {
            return n;
        }

        // 统计连续0的数量
        int[] left = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (array[i - 1] == '0') {
                left[i] += left[i - 1] + 1;
            } else {
                left[i] = left[i - 1];
            }
        }

        int ans = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = array[i];
            if (c == '1') {
                int retain = n - i;
                childLen = 0;
                if (retain < m) {
                    childLen = retain;
                } else {
                    dfs(array, i, sb);
                }
                ans = Math.max(ans, childLen + left[i]);
            }
        }

        return ans;
    }

    private int childLen = 0;
    private int kLen = 0;
    private String kStr;

    private void dfs(char[] ori, int x, StringBuilder sb) {
        if (x >= ori.length) {
            childLen = Math.max(childLen, sb.length());
            return;
        }

        if (childLen == kLen) return;

        for (int i = x; i < ori.length; i++) {
            int c = ori[i];
            sb.append(c);
            dfs(ori, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
