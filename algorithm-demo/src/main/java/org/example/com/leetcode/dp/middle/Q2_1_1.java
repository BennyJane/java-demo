package org.example.com.leetcode.dp.middle;


import java.util.ArrayList;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数
 * https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/
 */
public class Q2_1_1 {

    private int[] index;
    private int[] temp;
    private int[] tempIndex;
    private int[] ans;

    // 归并排序： 变形
    public List<Integer> countSmaller(int[] nums) {
        this.index = new int[nums.length];
        // temp tempIndex 存储修改后的数值与索引
        // 两者记录合并后的数组
        this.temp = new int[nums.length];
        this.tempIndex = new int[nums.length];
        this.ans = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            index[i] = i;
        }
        int l = 0, r = nums.length - 1;
        mergeSort(nums, l, r);
        List<Integer> list = new ArrayList<Integer>();
        for (int num : ans) {
            list.add(num);
        }
        return list;
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
                ans[index[i]] += (j - mid - 1);
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
            ans[index[i]] += (j - mid - 1);
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

    public static void main(String[] args) {
        Q2_1_1 q = new Q2_1_1();
        int[] people = new int[]{
//                5, 2, 6, 1
//                -1,-1
                2, 1, 0
//                1, 9, 7, 8, 5

        };
        q.countSmaller(people);
    }
}