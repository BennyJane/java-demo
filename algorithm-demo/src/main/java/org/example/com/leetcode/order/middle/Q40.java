package org.example.com.leetcode.order.middle;

import java.util.*;

/**
 * 39. 组合总和
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class Q40 {
    // 递归, 回溯法

    private Set<List<Integer>> res;

    // TODO 优化效率
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new HashSet<>();
        List<Integer> temp = new ArrayList<>();
        dfs(candidates, target, temp);
        System.out.println(res);
        return new ArrayList<>(res);
    }

    private void dfs(int[] nums, int target, List<Integer> ans) {
        if (target == 0) {
            // 重新生成集合，深拷贝
            List<Integer> temp = new ArrayList<>(ans);
            Collections.sort(temp);
            res.add(temp);
        }
        if (target < 0) {
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (val > target) {
                continue;
            }
            ans.add(val);
            dfs(nums, target - nums[i], ans);
            ans.remove(ans.size() - 1);
        }
    }

    public static void main(String[] args) {
        Q40 q = new Q40();
//        int[] nums = {2, 3, 6, 7};
//        q.combinationSum(nums, 7);
        int[] nums = {2, 3, 5};
        q.combinationSum(nums, 8);
    }
}


