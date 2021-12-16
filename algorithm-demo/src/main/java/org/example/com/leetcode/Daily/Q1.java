package org.example.com.leetcode.Daily;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/non-negative-integers-without-consecutive-ones/
 * 600. 不含连续1的非负整数
 */
public class Q1 {

    int ans = 0;
    int[] nums;

    public int findIntegers(int n) {
        List<Integer> list = new ArrayList<>();
        int ori = n;
        while (ori > 0) {
            int res = ori % 2;
            list.add(res);
            ori = ori / 2;
        }
        System.out.println(list);
        int len = list.size();
        nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[len - i - 1] = list.get(i);
        }

        int[] arr = new int[len];
        dfs(arr, 1);
        arr[0] = 1;
        dfs(arr, 1);
        return ans;
    }

    private void dfs(int[] arr, int index) {
        if (index >= arr.length) {
            // 需要判断是否<=n
            if (check(arr, nums)) {
                ans++;
            }
            return;
        }
        if (arr[index - 1] == 1) {
            arr[index] = 0;
            dfs(arr, index + 1);
        } else {
            arr[index] = 0;
            dfs(arr, index + 1);
            arr[index] = 1;
            dfs(arr, index + 1);
        }
    }

    private boolean check(int[] arr, int[] ori) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (arr[i] != ori[i]) {
                return ori[i] > arr[i];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q1 q = new Q1();
        q.findIntegers(8);
    }
}


