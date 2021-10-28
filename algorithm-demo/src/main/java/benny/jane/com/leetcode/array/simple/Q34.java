package benny.jane.com.leetcode.array.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 496. 下一个更大元素 I
 */
public class Q34 {
    // 哈希表
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums2.length;
        for (int i = 0; i < len; i++) {
            int cur = nums2[i];
            int right = i;
            while (right < len && nums2[right] <= cur) {
                right++;
            }
            if (right < len) {
                map.put(cur, nums2[right]);
            } else {
                map.put(cur, -1);
            }
        }

        int len2 = nums1.length;
        for (int i = 0; i < len2;i++) {
            nums1[i] = map.get(nums1[i]);
        }

        return nums1;
    }

    // 栈

}


