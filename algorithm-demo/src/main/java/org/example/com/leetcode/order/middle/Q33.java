package org.example.com.leetcode.order.middle;

import java.util.ArrayList;
import java.util.List;

public class Q33 {

    class Solution {
        public int[] rearrangeBarcodes(int[] barcodes) {
            // 统计单词出现的次数
            int n = barcodes.length;
            int[] cnt = new int[n];
            for (int barcode : barcodes) {
                cnt[barcode]++;
            }

            // 提取次数不为0的对象
            List<Integer> items = new ArrayList<>();
            for (int i = 1; i < cnt.length; i++) {
                if (cnt[i] != 0) {
                    items.add(i);
                }
            }
            // 根据次数降序排列
            items.sort((o1, o2) -> cnt[o2] - cnt[o1]);

            // 根据排序好的数组，重新生成包含所有原始数据的数组
            int[] cur = new int[n];
            int j = 0;
            for (int item : items) {
                for (int i = 0; i < cnt[item]; i++) {
                    cur[j++] = item;
                }
            }

            int[] res = new int[n];
            j = 0;
            // 先排奇数位置
            for (int i = 0; i < n; i += 2) {
                res[i] = cur[j++];
            }
            // 再排偶数位置
            for (int i = 1; i < n; i += 2) {
                res[i] = cur[j++];
            }
            return res;
        }
    }
}


