package com.benny.learning.algorithm.leetcode.array.middle;


import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和III
 * https://leetcode-cn.com/problems/combination-sum-iii/
 */
public class Q66 {

    private List<List<Integer>> ans;
    private boolean[] used = new boolean[10];
    private int limit;

    public List<List<Integer>> combinationSum3(int k, int n) {
        ans = new ArrayList<>();
        limit = k;
        List<Integer> list = new ArrayList<>();
        dfs(1, n, list, 0);
        return ans;
    }

    private void dfs(int index, int n, List<Integer> list, int sum) {
        if (list.size() == limit) {
            if (sum == n) {
                ans.add(new ArrayList<>(list));
            }
            return;
        }

        for (int i = index; i < 10; i++) {
            if (used[i]) {
                continue;
            }
            sum += i;
            list.add(i);
            used[i] = true;
            // FIXME 传入当前i；而不是index
            dfs(i + 1, n, list, sum);
            sum -= i;
            used[i] = false;
            list.remove(list.size() - 1);
        }

    }


    public static void main(String[] args) {
        Q66 q = new Q66();
        q.combinationSum3(3, 9);
    }
}


