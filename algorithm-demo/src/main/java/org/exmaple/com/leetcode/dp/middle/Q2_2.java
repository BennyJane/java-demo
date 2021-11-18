package org.exmaple.com.leetcode.dp.middle;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
public class Q2_2 {
    // 归并排序
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

    public static void main(String[] args) {
        Q2_2 q = new Q2_2();
        int[] nums = new int[]{
                7,5,6,4
        };
        q.reversePairs(nums);
    }

}


