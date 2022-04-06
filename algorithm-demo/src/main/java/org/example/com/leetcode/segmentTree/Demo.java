package org.example.com.leetcode.segmentTree;

import java.util.*;

/**
 * 剑指 Offer 51. 数组中的逆序对
 */
public class Demo {
    public static void main(String[] args) {

    }
}

/**
 * 归并排序
 */
class Solution {
    private int[] index;
    private int[] temp;
    private int[] tempIndex;
    private int ans;

    // 归并排序： 变形
    public int reversePairs(int[] nums) {
        this.index = new int[nums.length];
        // temp tempIndex 存储修改后的数值与索引
        // 两者记录合并后的数组
        this.temp = new int[nums.length];
        this.tempIndex = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            index[i] = i;
        }
        int l = 0, r = nums.length - 1;
        mergeSort(nums, l, r);
        return ans;
    }

    public void mergeSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) >> 1;
        mergeSort(a, l, mid);
        mergeSort(a, mid + 1, r);
        merge(a, l, mid, r);
    }

    public void merge(int[] a, int l, int mid, int r) {
        int i = l, j = mid + 1, p = l;
        // 将数组分为[l, mid] [mid+1, r] 两部分，然后考虑合并两个数组
        while (i <= mid && j <= r) {
            if (a[i] <= a[j]) {
                temp[p] = a[i];
                tempIndex[p] = index[i];
                // 只有当左指针移动时，才需要计算ans的数量
                // 此时，右指针移动的距离，就表示右侧小于当前值的数量
                ans += (j - mid - 1);
                ++i;    // 移动左侧指针
                ++p;
            } else {
                temp[p] = a[j];
                tempIndex[p] = index[j];
                ++j;    // 移动右侧指针，从mid+1开始
                ++p;
            }
        }
        // 继续移动左侧指针
        while (i <= mid) {
            temp[p] = a[i];
            tempIndex[p] = index[i];
            ans += (j - mid - 1);
            ++i;
            ++p;
        }
        // 继续移动右侧指针
        while (j <= r) {
            temp[p] = a[j];
            tempIndex[p] = index[j];
            ++j;
            ++p;
        }
        for (int k = l; k <= r; ++k) {
            index[k] = tempIndex[k];
            a[k] = temp[k];
        }
    }
}

/**
 * 二分查找排序
 */
class Solution1 {

    public int reversePairs(int[] nums) {
        int ans = 0;
        List<Integer> list = new ArrayList<>();
        for (int c : nums) {
            if (!list.isEmpty()) {
                int index = binarySearch(list, c);
                ans += (list.size() - index);
                list.add(index, c);
            } else {
                list.add(c);
            }
        }

        return ans;
    }

    private int binarySearch(List<Integer> tmp, int target) {
        int l = 0, r = tmp.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (tmp.get(mid) <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }

        }
        return l;
    }

    public static void main(String[] args) {
        Solution1 q = new Solution1();
        int[] array = {7, 5, 6, 4};
        q.reversePairs(array);
    }
}