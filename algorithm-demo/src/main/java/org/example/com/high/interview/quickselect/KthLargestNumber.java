package org.example.com.high.interview.quickselect;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 1985.找出数组中的第K大整数
 */
public class KthLargestNumber {
    public String kthLargestNumber1(String[] nums, int k) {
        int n = nums.length;
        // 小根堆： 堆顶为堆中最小值
        PriorityQueue<String> queue = new PriorityQueue(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                }
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }

        for (int i = k; i < n; i++) {
            String c = nums[i];
            String p = queue.peek();
            // 大于堆中最小值，即入堆
            if (c.length() > p.length()) {
                queue.add(c);
                queue.poll();
            }
            if (c.length() == p.length() && c.compareTo(p) > 0) {
                queue.add(c);
                queue.poll();
            }
        }

        return queue.peek();
    }

    // 快排思想
    public String kthLargestNumber(String[] nums, int k) {
        int n = nums.length;
        quickSort(nums, 0, n - 1, k);
        return nums[n - k];
    }

    private void quickSort(String[] arr, int L, int R, int k) {
        if (L == R) return;

        int randomIndex = new Random().nextInt(R - L + 1) + L;
        swap(arr, randomIndex, R);
        int pivotIndex = partition(arr, L, R);
        int rightSize = R - pivotIndex + 1;
        if (rightSize == k) {
            return;
        } else if (rightSize < k) {
            quickSort(arr, L, pivotIndex - 1, k - rightSize);
        } else {
            quickSort(arr, pivotIndex + 1, R, k);
        }
    }

    private int partition(String[] arr, int L, int R) {
        String pivot = arr[R];
        int start = L - 1;
        for (int i = L; i <= R; i++) {
            String c = arr[i];
            if (check(c, pivot) <= 0) {
                start++;
                swap(arr, start, i);
            }
        }

        return start;
    }

    private void swap(String[] arr, int L, int R) {
        String tmp = arr[L];
        arr[L] = arr[R];
        arr[R] = tmp;
    }

    private int check(String a, String b) {
        if (a.length() == b.length()) {
            return a.compareTo(b);
        }
        return a.length() - b.length();
    }

}
