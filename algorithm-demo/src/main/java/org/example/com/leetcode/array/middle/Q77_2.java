package org.example.com.leetcode.array.middle;

/**
 * 667. 优美的排列 II
 * https://leetcode-cn.com/problems/beautiful-arrangement-ii/
 */
public class Q77_2 {
    // 使用规律
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];

        // 构造等差数列，将可重复的差值部分构造为顺序差为1的序列
        // 长度： n - k -1
        for (int i = 0; i < n - k - 1; i++) {
            ans[i] = i + 1;
        }

        // 构造交错梳理额，范围[n - k -1, n] 左右两端的数据一次构成一个相邻的两个数
        int flag = 0;

        int left = n - k;
        int right = n;
        for (int i = n - k - 1; i < n; i++) {
            if (flag % 2 == 0) {
                ans[i] = left;
                left++;
            } else {
                ans[i] = right;
                right--;
            }
            flag++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Q77_2 q = new Q77_2();
//        q.constructArray(5, 4);
        q.constructArray(92, 80);
    }
}


