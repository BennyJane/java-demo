package org.example.com.leetcode.array.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * https://leetcode-cn.com/problems/subsets/
 */
public class Q58 {

    private List<List<Integer>> ans;

    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        List<Integer> arr = new ArrayList<>();
        dfs(nums, 0, arr);
        System.out.println(ans);
        return ans;
    }

    private void dfs(int[] nums, int i, List<Integer> arr) {
        if (i >= nums.length) {
            ans.add(new ArrayList<>(arr));
        } else {
            dfs(nums, i + 1, arr);   // 不包含该数值
            int temp = nums[i];
            arr.add(temp);
            dfs(nums, i + 1, arr);
            arr.remove(arr.size() - 1); //包含该数值

        }
    }

    public static void main(String[] args) {
        Q58 q = new Q58();
        int[] nums = new int[]{1, 2, 3};
        q.subsets(nums);
    }
}


