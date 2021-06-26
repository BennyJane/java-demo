package com.benny.learning.algorithm.leetcode.array.simple;

/**
 * 556. 下一个更大元素 III
 * https://leetcode-cn.com/problems/next-greater-element-iii/
 */
public class Q36 {
    public int nextGreaterElement(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            char cur = arr[i];
            int right = i;
            while (right < len && arr[right] - cur <= 0) {
                right++;
            }
            if (right < len) {
                arr[i] = arr[right];
                arr[right] = cur;
                StringBuffer sb = new StringBuffer();
                for (char c: arr) {
                    sb.append(c);
                }
                return Integer.parseInt(sb.toString());
            }
        }

        return -1;
    }
}


