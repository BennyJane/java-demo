package benny.jane.com.leetcode.array.middle;

/**
 * 526. 优美的排列
 * https://leetcode-cn.com/problems/beautiful-arrangement/
 */
public class Q76 {

    private boolean[] used;
    private int ans = 0;

    public int countArrangement(int n) {
        used = new boolean[n];
        dfs(n, n);
        return ans;
    }

    private void dfs(int n, int k) {
        if (k == 0) {
            ans++;
            return;
        }
        for (int i = n; i > 0; i--) {
            int index = i - 1;
            if (i % k == 0 || k % i == 0) { // 满足条件
                if (used[index]) {
                    continue;
                }
                used[index] = true;
                dfs(n, k - 1);
                used[index] = false;
            }
        }
    }

    public static void main(String[] args) {
        Q76 q = new Q76();

        q.countArrangement(2);
    }
}


