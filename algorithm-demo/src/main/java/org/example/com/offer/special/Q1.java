package org.example.com.offer.special;

import org.example.com.Q;

//494. 目标和
public class Q1 {

    private int cnt = 0;

    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, 0, target);
        return cnt;
    }

    public void dfs(int[] array, int index, int rest) {
        if (index == array.length) {
            if (rest == 0) cnt++;
            return;
        }
        int res1 = rest + array[index];
        dfs(array, index + 1, res1);
        int res2 = rest - array[index];
        dfs(array, index + 1, res2);
    }

    public static void main(String[] args) {
        Q1 q = new Q1();
        int[] nums = new int[] {1,1,1,1,1};
        int t = 3;
        q.findTargetSumWays(nums, t);
    }
}
