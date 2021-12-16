package org.example.com.leetcode.order.middle;

import java.util.*;

/**
 * 40. 组合总和 II
 * https://leetcode-cn.com/problems/combination-sum-ii/
 * 参考 Q39 同类题目，需要优化效率
 */
public class Q47 {

    private Set<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new HashSet<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, temp, 0);
        System.out.println(res);
        return new ArrayList<>(res);
    }

    private void dfs(int[] nums, int target, List<Integer> ans, int index) {
        if (target == 0) {
            List<Integer> temp = new ArrayList<>(ans);
            Collections.sort(temp);
            res.add(temp);
        }
        if (target < 0) {
            return;
        }

        if (index >= nums.length) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            int val = nums[i];
            if (val > target) {
                break;
            }
            ans.add(val);
            // TODO 需要增加的是i， 而不是index
            dfs(nums, target - nums[i], ans, i + 1);
            ans.remove(ans.size() - 1);
        }
    }

    public static void main(String[] args) {
        Q47 q = new Q47();
//        int[] nums = {10, 1, 2, 7, 6, 1, 5};
//        q.combinationSum2(nums, 8);

        int[] nums = {1, 2};
        q.combinationSum2(nums, 4);
    }
}


