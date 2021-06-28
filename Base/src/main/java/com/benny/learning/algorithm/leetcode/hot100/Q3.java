package com.benny.learning.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * https://leetcode-cn.com/problems/permutations/
 */
public class Q3 {

    private List<List<Integer>> ans = new ArrayList<>();
    private boolean[] visited;

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        visited = new boolean[nums.length];
        dfs(nums, list);
        return ans;
    }

    private void dfs(int[] nums, List<Integer> list) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                int cur = nums[i];
                list.add(cur);
                visited[i] = true;
                dfs(nums, list);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }
}


