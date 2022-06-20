package org.example.com.match.No298;

//2311. 小于等于 K 的最长二进制子序列
public class Q3 {
    public static void main(String[] args) {
        Q3 q = new Q3();
//        q.longestSubsequence("1001010", 5);
//        q.longestSubsequence("00101001", 1);
        q.longestSubsequence("001010101011010100010101101010010", 93951055);
    }

    // 贪心思想
    public int longestSubsequence(String s, int k) {
        String kB = Integer.toBinaryString(k);
        int m = kB.length();
        int n = s.length();

        // 小于k的二进制：长度小于 或 每个位置数值小于等于k同位置数字
        if (n < m) return n;

        // 截取尾部长度为m的字符串
        String tail = s.substring(n - m);
        // 根据tail 与 kB的大小，决定长度取 m 或 m - 1
        // 当tail大于kB时，一定是某个位置上 tail是1 而 kB为0，
        // 因此为了确保tail <= kB，要么长度减一；
        // 或者 在前缀中寻找一个0替换当前位置的1，那么前缀0的数量就会减少1，效果不会优于tail长度减1
        int length = tail.compareTo(kB) > 0 ? m - 1 : m;
        // 统计s前缀[0, n -m) 范围内前缀0的数量
        for (char c : s.substring(0, n - m).toCharArray()) {
            if (c == '0') length++;
        }
        return length;
    }

    public int longestSubsequence2(String s, int k) {
        int n = s.length(), m = 32 - Integer.numberOfLeadingZeros(k);
        if (n < m) return n;
        int ans = Integer.parseInt(s.substring(n - m), 2) <= k ? m : m - 1;
        return ans + (int) s.substring(0, n - m).chars().filter(c -> c == '0').count();
    }

    public int longestSubsequence3(String s, int k) {
        int n = s.length(), m = 32 - Integer.numberOfLeadingZeros(k);
        if (n < m) return n;
        int ans = Integer.parseInt(s.substring(n - m), 2) <= k ? m : m - 1;
        return ans + (int) s.substring(0, n - m).chars().filter(c -> c == '0').count();
    }
}

