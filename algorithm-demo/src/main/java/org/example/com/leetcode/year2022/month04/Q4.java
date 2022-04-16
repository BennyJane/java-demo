package org.example.com.leetcode.year2022.month04;


public class Q4 {

}

// 307.区域和检索-数组可修改

// 树状数组
class NumArray {
    int[] tree;
    int n;
    int[] data;

    int lowBit(int x) {
        return x & (~x + 1);
    }

    void add(int index, int val) {
        for (int i = index; i <= n; i += lowBit(i)) {
            tree[i] += val;
        }
    }

    int query(int index) {
        int preSum = 0;
        for (int i = index; i > 0; i -= lowBit(i)) {
            preSum += tree[i];
        }
        return preSum;
    }

    public NumArray(int[] nums) {
        data = nums;
        n = nums.length;
        tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            add(i + 1, nums[i]);
        }
    }


    public void update(int index, int val) {
        add(index + 1, val - data[index]);
        data[index] = val;
    }

    public int sumRange(int left, int right) {
        return query(right + 1) - query(left);
    }
}


