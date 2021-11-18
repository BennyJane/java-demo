package org.exmaple.com.leetcode.array.middle;

import java.util.HashSet;
import java.util.Set;

/**
 * 667. 优美的排列 II
 * https://leetcode-cn.com/problems/beautiful-arrangement-ii/
 */
public class Q77 {
    private boolean[] used;
    private int[] ans;

    public int[] constructArray(int n, int k) {
        ans = new int[n];
        used = new boolean[n];

        dfs(n, k, 0, new int[n]);

        return ans;
    }

    private void dfs(int n, int k, int index, int[] arr) {
        if (index == n) {
            if (check(arr, k)) {
                System.arraycopy(arr, 0, ans, 0, arr.length);
            }
            return;
        }

        for (int num = n; num >= 1; num--) {
            if (used[num - 1]) {  // 该值已经被使用
                continue;
            }
            used[num - 1] = true;
            arr[index] = num;
            dfs(n, k, index + 1, arr);
            used[num - 1] = false;
        }
    }

    private boolean check(int[] arr, int k) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length - 1; i++) {
            set.add(Math.abs(arr[i] - arr[i + 1]));
            if (set.size() > k) {
                return false;
            }
        }
        if (set.size() != k) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Q77 q = new Q77();
        q.constructArray(5,4);
    }
}


