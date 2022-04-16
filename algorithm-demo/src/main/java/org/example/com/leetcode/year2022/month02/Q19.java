package org.example.com.leetcode.year2022.month02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q19 {
    // FIXME arr内地数字：不是恰好为[1:n]
    public List<Integer> pancakeSort(int[] arr) {
        int n = arr.length;
        int[] sortedArr = new int[n];
        for (int i = 0; i < n; i++) {
            sortedArr[i] = arr[i];
        }
        Arrays.sort(sortedArr);

        List<Integer> res = new ArrayList<Integer>();
        for (int i = n - 1; i >= 0; i--) {
            int curMax = sortedArr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] == curMax) {
                    // 记录的是索引
                    res.add(j+1);
                    res.add(i+1);
                    arr = swap(arr, 0, j);
                    arr = swap(arr, 0, i);
                }
            }
        }
        return res;
    }

    private int[] swap(int[] arr, int x, int y) {
        while (x < y) {
            int tmp = arr[y];
            arr[y] = arr[x];
            arr[x] = tmp;
            x++;
            y--;
        }
        return arr;
    }

    public List<Integer> pancakeSortError(int[] arr) {
        int n = arr.length;

        List<Integer> ans = new ArrayList<Integer>();
        for (int i = n; i >= 1; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (arr[j] == i) {
                    ans.add(arr[j]);
                    ans.add(arr[i - 1]);
                    arr = swap(arr, 0, j);
                    arr = swap(arr, 0, i - 1);
                }
            }
        }
        return ans;
    }
}

class Solution {
    public List<Integer> pancakeSort(int[] arr) {
        int len = arr.length;
        int[] sortedNums = new int[len];
        for (int i = 0; i < len; i++) {
            sortedNums[i] = arr[i];
        }
        Arrays.sort(sortedNums);

        List<Integer> list = new ArrayList();
        int retainLen = len;
        while (retainLen > 0) {
            int max = sortedNums[retainLen - 1];
            int maxIndex = 0;
            for (int i = 0; i < retainLen; i++) {
                if (arr[i] == max) {
                    maxIndex = i;
                    break;
                }
            }
            if (maxIndex == retainLen -1) {
                retainLen--;
                continue;
            }
            // 将当前最大值翻转到首位
            arr = change(arr, 0, maxIndex);
            // 将最大值翻转到尾部
            arr = change(arr, 0, retainLen - 1);
            list.add(maxIndex+1);
            list.add(retainLen);
            // 完成最大值排序
            retainLen--;
        }

        return list;
    }

    private int[] change(int[] nums, int l, int r) {
        int left = l;
        int right = r;
        while (left < right) {
            int leftVal = nums[left];
            int rightVal = nums[right];
            nums[left] = rightVal;
            nums[right] = leftVal;
            left++;
            right--;
        }
        return nums;
    }
}