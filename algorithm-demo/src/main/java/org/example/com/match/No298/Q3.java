package org.example.com.match.No298;

public class Q3 {
    public static void main(String[] args) {
        Q3 q = new Q3();
//        q.longestSubsequence("1001010", 5);
//        q.longestSubsequence("00101001", 1);
        q.longestSubsequence("001010101011010100010101101010010", 93951055);
    }

    public int longestSubsequence(String s, int k) {
        String target = Integer.toBinaryString(k);
        char[] kArr = target.toCharArray();
        int m = kArr.length;

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
        for (int i = 0; i < n; i++) {
            char c = array[i];
            if (c == '1') {
                int retain = n - i;
                childLen = 0;
                if (retain < m) {
                    childLen = retain;
                } else {
                    dfs(array, kArr, i, 0, 0);
                }
                ans = Math.max(ans, childLen + left[i]);
            }
        }

        return ans;
    }

    private int childLen = 0;

    private void dfs(char[] ori, char[] target, int x, int y, int len) {
        if (x >= ori.length) {
            childLen = Math.max(childLen, len);
            return;
        }

        if (y >= target.length) {
            childLen = target.length;
            return;
        }

        if (childLen == target.length) return;

        char b = target[y];
        for (int i = x; i < ori.length; i++) {
            char a = ori[i];
            if (a >= b) {
                dfs(ori, target, i + 1, y + 1, len + 1);
            }
        }
    }

}
