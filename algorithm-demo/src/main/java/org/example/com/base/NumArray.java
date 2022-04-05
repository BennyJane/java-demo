package org.example.com.base;

/**
 * 分块数组
 * https://leetcode-cn.com/problems/range-sum-query-mutable/solution/qu-yu-he-jian-suo-shu-zu-ke-xiu-gai-by-l-76xj/
 */
public class NumArray {

    int[] sum;
    int[] data;
    int size;

    public NumArray(int[] nums) {
        int n = nums.length;
        data = nums;
        // TODO 核心 计算平方根
        size = (int) Math.sqrt(n);
        // 实现 n/size 向上取整
        sum = new int[(n + size - 1) / size];
        for (int i = 0; i < n; i++) {
            sum[i / size] += nums[i];
        }
    }

    public void update(int index, int val) {
        // 更新和变化
        sum[index / size] += val - data[index];
        data[index] = val;
    }

    public int sumRange(int left, int right) {
        // 计算区间对应sum中的范围
        int b1 = left / size, e1 = left % size;
        int b2 = right / size, e2 = right % size;
        if (b1 == b2) {
            // 同一个区间
            int ans = 0;
            // [e1, e2]
            for (int i = e1; i <= e2; i++) {
                // 数据还是从原数组获取
                ans += data[b1 * size + i];
            }
            return ans;
        }

        int ans = 0;
        // 计算b1区间累计和: [e1, size)
        for (int i = e1; i < size; i++) {
            ans += data[b1 * size + i];
        }
        // 计算b2区间累计和: [0, e2]
        for (int i = 0; i <= e2; i++) {
            ans += data[b2 * size + i];
        }
        // 计算(b1, b2)区间和
        for (int i = b1 + 1; i < b2; i++) {
            ans += sum[i];
        }

        return ans;
    }

}
