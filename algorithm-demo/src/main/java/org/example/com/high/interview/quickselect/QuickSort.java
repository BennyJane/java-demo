package org.example.com.high.interview.quickselect;

import java.util.Random;

public class QuickSort {

    public int[] core(int[] arr) {
        int n = arr.length;
        if (n == 0) return arr;
        dfs(arr, 0, n - 1);
        return arr;
    }

    /**
     * 分值处理区间[l ,r]
     * 随机选择pivot，将区间内数值分为两部分，左侧小于等于pivot，右侧大于pivot
     * 返回分界点索引index
     *
     * @param arr
     * @param l
     * @param r
     */
    private void dfs(int[] arr, int l, int r) {
        // 递归终止条件：区间长度 <= 1
        if (l >= r) {
            return;
        }
        // nextInt: 返回值范围 (0 , bound) 两个端点都i不会取
        // FIXME： 随机值 + 左端点索引（不是1）
        int randomIndex = new Random().nextInt(r - l + 1) + l;
        swap(arr, randomIndex, r);
        int pivotIndex = partition(arr, l, r);
        // pivotIndex 对应位置的值已经确定位置，不需要再参与后续排序处理
        dfs(arr, l, pivotIndex - 1);
        dfs(arr, pivotIndex + 1, r);
    }

    private int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        // left: 记录用于交换 <= pivot的值的位置索引，每次交换前，需要加一
        int left = l - 1;
        // 遍历[l, r]区间，将 <= pivot的值，全部交换到left + 1的位置
        for (int i = l; i <= r; i++) {
            if (arr[i] <= pivot) {
                left++;
                swap(arr, left, i);
            }
        }
        return left;
    }


    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}

