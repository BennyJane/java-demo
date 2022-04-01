package org.example.com.bitMapDemo;

import java.util.*;

/**
 * 基于BitMap实现百万数据排序
 */
public class Demo {
    public static void main(String[] args) {
        // 百万数据排序
        method1();
        int[] array = {0, 1, 25, 52, 544, 4, 2, 45, 52, 2, 45, 52, 245, 32};
        method2(Arrays.stream(array).max().getAsInt() + 1, array);
    }

    public static int[] initBitMap(int maxValue) {
        // Integer.MAX_VALUE 2147483647 长度为10
        int length = maxValue / 32 + 1;
        int[] bitMap = new int[length];
        return bitMap;
    }

    public static void method1() {
        // Integer.MAX_VALUE 2147483647 长度为10
        int target = 10000;
        // 初始化bitMap
        int[] bitMap = initBitMap(target);

        int[] array = {0, 1, 25, 52, 544, 4, 2, 45, 52, 2, 45, 52, 245, 32};
        Set<Integer> set = new HashSet<>();
        // 将原始数据 映射到 bitMap
        for (int v : array) {
            // 计算 除以32的余数, 等价 v / 32
            int index = v >> 5;
            // 计算 取模运算, 等效 v % 32
            int offset = v & 31;

            int old = bitMap[index];
            // 赋值操作
            bitMap[index] = bitMap[index] | (1 << offset);
            if (old == bitMap[index]) {
                System.out.println(v + ": is exits.");
            }
            set.add(v);
        }

        System.out.println("[set]: \n" + set);
        // 将BitMap还原为数组： 去重并排序（与原数组不同）
        List<Integer> sortedArr = new ArrayList<>();
        for (int i = 0; i < bitMap.length; i++) {
            int b = bitMap[i];
            // 跳过为0的取值
            if (b == 0) {
                continue;
            }
            for (int j = 0; j < 32; j++) {
                int cur = (b >> j) & 1;
                if (cur == 1) {
                    sortedArr.add(32 * i + j);
                }
            }
        }
        System.out.println("[sortedArr]: \n" + sortedArr);
    }

    public static void method2(int length, int[] data) {
        int[] bitMap = initBitMap(length);
        Set<Integer> set = new HashSet<>();
        // 将原始数据 映射到 bitMap
        for (int v : data) {
            int index = v / 32;
            int offset = v % 32;

            int old = bitMap[index];
            // 赋值操作
            bitMap[index] = bitMap[index] | (1 << offset);
            if (old == bitMap[index]) {
                System.out.println(v + ": is exits.");
            }
            set.add(v);
        }

        System.out.println(set);
        // 将BitMap还原为数组： 去重并排序（与原数组不同）
        List<Integer> sortedArr = new ArrayList<>();
        for (int i = 0; i < bitMap.length; i++) {
            int b = bitMap[i];
            if (b == 0) {
                continue;
            }
            for (int j = 0; j < 32; j++) {
                // cur = (b >> j) & 0x01 ==> if (cur == 0x01)
                int cur = b & (1 << j);
                System.out.println(cur);
                // FIXME 计算结果cur 不是1 或 0， 不能使用 == 1 来判断
                if (cur > 0) {
                    sortedArr.add(32 * i + j);
                }
            }
        }
        System.out.println(sortedArr);
    }

}
