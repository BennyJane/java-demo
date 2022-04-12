package org.example.com.high.interview.quickselect;

import java.util.Random;

/**
 * 最小K个数
 */
class SmallestK2 {
    // TODO 快排思想
    public int[] smallestK(int[] arr, int k) {
        int[] ans = new int[k];
        // 快速退出：k = 0
        if (k == 0) return ans;
        int n = arr.length;
        // 快排区间:[0, n - 1]
        quickSort(arr, 0, n - 1, k);
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    private void quickSort(int[] arr, int l, int r, int k) {
        // 长度为1，不需要排序
        // FIXME 不能使用 l == r , 当k=0时，会报错
        // 当k=0时，r会出现负数
        if (l >= r) {
            // l >= r 包含l==r的情况
            return;
        }

        // 随机选择一个值pivot，并将数组划分为小于等于pivot，大于pivot的两部分
        // 返回分界点索引: arr[pos] = pivot, [0, pos]区间值都 <= pivot
        int pos = randomPartition(arr, l, r);
        // 计算左区间长度，左区间全部值 <= pivot
        int length = pos - l + 1;
        // pos 不满足条件时，递归函数应该排除该索引位置
        if (k == length) {
            // 左区间长度恰好等于k，即pivot是第K小的值，[0, pos]
            return;
        } else if (k < length) {
            // 前k小的值位于左区间
            // TODO: 右端点必须 - 1，缩小范围，因为pos索引不满足条件，直接去除
            quickSort(arr, l, pos - 1, k);
        } else {
            // TODO: 只需要在右区间上再查询（k - length）个最小值，就满足题目条件
            quickSort(arr, pos + 1, r, k - length);
        }
    }

    private int randomPartition(int[] nums, int l, int r) {
        // 在区间[l, r]上随机选择一个值
        int index = new Random().nextInt(r - l + 1) + l;
        // 左侧区间保存小于等于 nums[index]的数据
        // 右侧区间保存大于nums[index]的值
        // 将nums[index]的值放到末尾，省略将index传入下层函数
        // pivot = nums[index] => pivot = nums[r]
        swap(nums, r, index);
        return partition(nums, l, r);
    }

    // 将大于pivot的值，移动到数组左侧，从l索引依次往后排列
    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        // left + 1: 表示存储小于pivot的下一个位置
        int left = l - 1;
        // 索引r最后处理， j [l, r)
        for (int j = l; j <= r - 1; j++) {
            // FIXME 包含pivot
            if (nums[j] <= pivot) {
                // 计算下一个存储小于pivot值的位置
                left += 1;
                swap(nums, left, j);
            }
        }
        // 将末尾的pivot值移动到左侧
        left += 1;
        swap(nums, left, r);
        // 返回临界点：left + 1 对应值大于pivot
        return left;
    }


    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}