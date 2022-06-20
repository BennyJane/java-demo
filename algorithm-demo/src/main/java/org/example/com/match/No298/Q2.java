package org.example.com.match.No298;

import java.util.ArrayList;
import java.util.List;

public class Q2 {
    public static void main(String[] args) {
        Q2 q = new Q2();
//        q.minimumNumbers(4, 0);
//        q.minimumNumbers(3000, 1);
        // 奇偶数判断
        q.minimumNumbers(947, 2);
    }

    private int ans = Integer.MAX_VALUE;
    private List<Integer> array;

    public int minimumNumbers(int num, int k) {
        array = new ArrayList<>();
        for (int i = num; i >= 1; i--) {
            int tail = i % 10;
            if (tail == k) {
                array.add(i);
            }
        }

        dfs(num, k, 0);
        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }

    private boolean dfs(int retain, int k, int count) {
        if (retain == 0) {
            ans = Math.min(ans, count);
            return true;
        }

        if (retain < k || count >= ans) {
            return false;
        }

        for (int c : array) {
            if (c > retain) {
                continue;
            }
            if (dfs(retain - c, k, count + 1)) {
                return dfs(retain - c, k, count + 1);
            }
        }
        return false;
    }
}
