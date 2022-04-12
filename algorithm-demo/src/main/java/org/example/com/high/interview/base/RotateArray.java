package org.example.com.high.interview.base;

import java.util.ArrayList;
import java.util.List;

public class RotateArray {
}


/**
 * 33. 搜索旋转排序数组
 * 数组内值互不相等
 */
class Solution0 {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        int pivot = nums[0];

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = nums[mid];
            // 快速退出： val = target
            if (val == target) {
                return mid;
            }
            // 判断mid值位于左右两个区间中哪一个
            if (val >= pivot) {
                // target 不可能等于 val
                if (target >= pivot && target < val) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > val && target < pivot) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}


/**
 * 10.03 搜索旋转数组
 * 数组内存在相等值
 * 滚动数组
 */
class Solution1 {
    public int search2(int[] arr, int target) {
        List<Integer> list = new ArrayList();
        for (int c : arr) {
            list.add(c);
        }
        return list.indexOf(target);
    }

    public int search(int[] arr, int target) {
        int n = arr.length;
        int left = 0, right = n - 1;

        int pivot = arr[0];

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = arr[mid];
            if (val < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return 0;
    }
}