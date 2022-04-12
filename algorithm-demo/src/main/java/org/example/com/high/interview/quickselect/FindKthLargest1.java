package org.example.com.high.interview.quickselect;

/**
 * 建立大根堆，执行 k -1 次删除后， 堆顶元素就是目标值
 */
public class FindKthLargest1 {
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        int n = nums.length;
        for (int i = n - 1; i >= (n - k + 1); i--) {
            swap(nums, 0, i);
            heapSize--;
            maxHeapify(nums, 0, heapSize);
        }

        return nums[0];
    }

    // 构建大根段： O(lng(n))
    private void buildMaxHeap(int[] arr, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeapify(arr, i, heapSize);
        }
    }

    public void maxHeapify(int[] arr, int i, int heapSize) {
        int largest = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, largest, heapSize);
        }
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
