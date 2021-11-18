package org.exmaple.com.leetcode.tanxin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/pancake-sorting/
 * 969. 煎饼排序
 */
public class Q2 {
    // 类似冒泡方式：每次优先将最大值放到尾部
    public List<Integer> pancakeSort(int[] arr) {
        int len = arr.length;
        int[] sortedNums = new int[len];
        for (int i = 0; i < len; i++) {
            sortedNums[i] = arr[i];
        }
        Arrays.sort(sortedNums);

        List<Integer> list = new ArrayList<>();
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
            // 剪枝操作
            if (maxIndex == retainLen - 1) {
                retainLen--;
                continue;
            }
            // 将当前最大值翻转到首位
            arr = change(arr, 0, maxIndex);
            // 将最大值翻转到尾部
            arr = change(arr, 0, retainLen - 1);
            list.add(maxIndex + 1);
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


    // 官方：
    public List<Integer> pancakeSort2(int[] arr) {
        List<Integer> ans = new ArrayList();
        int N = arr.length;

        // TODO 充分利用arr的取值是从1开始连续取值的特性
        Integer[] B = new Integer[N];
        // B 存储索引+1，即k的值
        for (int i = 0; i < N; ++i){
            B[i] = i + 1;
        }
        // 将索引按照对应位置上的值降序排列
        Arrays.sort(B, (i, j) -> arr[j - 1] - arr[i - 1]);

        for (int i : B) {
            // 索引位置，因为前面的翻转会发生变化
            for (int f : ans){
                if (i <= f){
                    i = f + 1 - i;
                }
            }
            // 完成两次翻转
            ans.add(i);
            ans.add(N--);
        }

        return ans;
    }

    public static void main(String[] args) {
        Q2 q = new Q2();
        int[] nums = new int[]{3, 2, 4, 1};
        q.pancakeSort(nums);
    }
}


