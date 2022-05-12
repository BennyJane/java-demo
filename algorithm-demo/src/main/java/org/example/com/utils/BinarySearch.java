package org.example.com.utils;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = i;
        }

        // 查找第一个大于等于 <key>的索引位置
        // toIndex 不包含在内
        int index = Arrays.binarySearch(nums, 0, 100, 99);
        System.out.println(index);
//        System.out.println(nums[index]);

        System.out.println(1 >> 1);
    }
}
