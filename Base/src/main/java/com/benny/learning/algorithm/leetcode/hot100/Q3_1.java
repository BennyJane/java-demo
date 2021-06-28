package com.benny.learning.algorithm.leetcode.hot100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 47. 全排列 II
 * https://leetcode-cn.com/problems/permutations-ii/
 * 包含重复数据
 */
public class Q3_1 {

    // 直接使用hashset对最后结果去重
    private Set<List<Integer>> ans = new HashSet<>();
    private boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> list = new ArrayList<>();
        visited = new boolean[nums.length];
        dfs(nums, list);
        return new ArrayList<>(ans);
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


