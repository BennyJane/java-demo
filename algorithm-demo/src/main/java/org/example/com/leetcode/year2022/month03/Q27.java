package org.example.com.leetcode.year2022.month03;

import java.util.Arrays;

//2028. 找出缺失的观测数据
public class Q27 {
    int[] ans;
    boolean isStop = false;

    public int[] missingRolls(int[] rolls, int mean, int n) {
        ans = new int[n];
        int sum = Arrays.stream(rolls).sum();
        int retain = (rolls.length + n) * mean - sum;

        if (retain < n || retain > 6 * n) {
            return new int[0];
        }

        dfs(retain, 0, n, new int[n]);
        if (Arrays.stream(ans).sum() != retain) {
            return new int[0];
        }
        return ans;
    }

    private void dfs(int retain, int index, int n, int[] res) {
        if (index >= n) {
            if (retain == 0) {
                ans = Arrays.copyOfRange(res, 0, n);
                isStop = true;
            }
            return;
        }
        if (retain <= 0) {
            return;
        }
        for (int i = 6; i > 0; i++) {
            if (isStop) {
                return;
            }
            res[index] = i;
            dfs(retain - i, index + 1, n, res);
            res[index] = 0;
        }
    }

    // 贪心思想
    public int[] missingRolls2(int[] rolls, int mean, int n) {
        int[] res = new int[n];
        int sum = Arrays.stream(rolls).sum();
        int retain = (rolls.length + n) * mean - sum;

        if (retain < n || retain > 6 * n) {
            // FIXME 不能使用 new int[6];
            return new int[0];
        }

        int avg = retain / n;
        int other = retain - avg * n;
        for (int i = 0; i < n; i++) {
            if (other > 0) {
                other--;
                res[i] = avg + 1;
            } else {
                res[i] = avg;
            }
        }

        return res;
    }

    public int[] missingRolls3(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int cnt = m + n;
        int sum = mean * cnt;
        for (int r : rolls) {
            sum -= r;
        }
        if (sum < n || sum > n * 6) return new int[0];

        int[] ans = new int[n];
        int avg = sum / n;
        Arrays.fill(ans, avg);
        for (int i = 0; i < (sum - avg * n); i++) {
            ans[i]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Q27 q = new Q27();
        int[] arr = {4, 5, 6, 2, 3, 6, 5, 4, 6, 4, 5, 1, 6, 3, 1, 4, 5, 5, 3, 2, 3, 5, 3, 2, 1, 5, 4, 3, 5, 1, 5};
        int k = 4;
        int n = 40;
//        q.missingRolls(arr, k, n);
        q.missingRolls2(arr, k, n);
    }
}
