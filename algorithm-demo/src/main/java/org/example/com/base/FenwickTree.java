package org.example.com.base;

/**
 * 树状数组
 */
public class FenwickTree {
    int[] inputData;
    int n;
    // 数据转存入tree中
    int[] tree;

    public FenwickTree(int[] nums) {
        inputData = nums;
        n = nums.length;
        // 原数组nums中索引和tree数组索引映射关系： indexNum + 1 = treeIndex
        tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            add(i + 1, nums[i]);
        }
    }

    // 计算x转二进制后，从右往左第一个1表示的数值
    // 计算每个值插入tree的索引位置
    int lowBit(int x) {
        // 等效 x & (~x +1)
        return x & -x;
    }

    /**
     * 向树状数组中插入值,每次需要修改[x, n]区间内的值，索引需要通过lowBit重新计算
     *
     * @param x   插入索引
     * @param val 值
     */
    void add(int x, int val) {
        // FIXME 区间范围；每次递增的值 lowBit(i)
        // 可以等于n
        for (int i = x; i <= n; i += lowBit(i)) {
            tree[i] += val;
        }
    }

    // 查询区间[0, index]范围内前缀和
    // 查询前缀和
    int query(int index) {
        int ans = 0;
        // 大于 0
        for (int i = index; i > 0; i -= lowBit(i)) {
            ans += tree[i];
        }
        return ans;
    }

    /**
     * 更新输入数组的值
     *
     * @param index 输入数组（原数组）索引
     * @param val
     */
    public void update(int index, int val) {
        // 更新变化量
        add(index + 1, val - inputData[index]);
        inputData[index] = val;
    }

    // 查询区间和
    public int sumRange(int left, int right) {
        return query(right + 1) - query(left);
    }


    public static void main(String[] args) {
        System.out.println(10);
        System.out.println(-10);
        System.out.println(~10);
        System.out.println(10 & -10);
        System.out.println(10 & (~10 + 1));
    }

}
