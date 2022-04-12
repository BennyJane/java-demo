package org.example.com.high.interview.quickselect;

import java.util.Random;

/**
 * 快速排序
 */
public class FindKthLargest2 {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        quickSort(nums, 0, n - 1, k);
        return nums[n - k];
    }

    private void quickSort(int[] arr, int l, int r, int k) {
        if (l >= r) {
            return;
        }

        int pos = randomAndSort(arr, l, r);
        // TODO 计算右区间的长度
        int rightSize = r - pos + 1;
        if (rightSize == k) {
            return;
        } else if (rightSize < k) {
            quickSort(arr, l, pos - 1, k - rightSize);
        } else {
            quickSort(arr, pos + 1, r, k);
        }
    }

    private int randomAndSort(int[] arr, int l, int r) {
        // nextInt: [0, upper) 输入末尾索引不包含在内
        int index = new Random().nextInt(r - l + 1) + l;
        swap(arr, r, index);
        return partition(arr, l, r);
    }

    private int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int nxtPivotIndex = l - 1;
        // TODO 将[l ,r]返回内，小于等于pivot的值，全部向前移动
        for (int i = l; i <= r; i++) {
            if (arr[i] <= pivot) {
                nxtPivotIndex++;
                swap(arr, nxtPivotIndex, i);
            }
        }
        return nxtPivotIndex;
    }


    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
