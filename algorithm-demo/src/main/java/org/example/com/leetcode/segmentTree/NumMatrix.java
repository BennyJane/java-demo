package org.example.com.leetcode.segmentTree;

/**
 * 308.二维区域和检索-可变
 */
public class NumMatrix {
    int[] tree;
    int[] data;
    int total;

    int m;
    int n;

    public NumMatrix(int[][] matrix) {
        m = matrix[0].length;
        n = matrix[0].length;
        total = m * n;
        data = new int[total];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                data[index] = matrix[i][j];
            }
        }

        tree = new int[total * 4];
        buildSegTree(0, 0, total - 1);
    }

    private void buildSegTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int mid = l + (r - l) / 2;
        int leftIndex = treeIndex * 2 + 1;
        int rightIndex = treeIndex * 2 + 2;

        buildSegTree(leftIndex, l, mid);
        buildSegTree(rightIndex, mid + 1, r);

        tree[treeIndex] = tree[leftIndex] + tree[rightIndex];
    }


    public void update(int row, int col, int val) {
        updateDfs(0, 0, total - 1, row * n + col, val);
    }

    private void updateDfs(int treeIndex, int l, int r, int index, int val) {
        if (l == r) {
            tree[treeIndex] = val;
            return;
        }

        int mid = l + (r - l) / 2;
        int left = treeIndex * 2 + 1;
        int right = treeIndex * 2 + 2;
        if (index > mid) {
            updateDfs(right, mid + 1, r, index, val);
        } else {
            updateDfs(left, l, mid, index, val);
        }

        tree[treeIndex] = tree[left] + tree[right];
    }


    public int sumRegion(int row1, int col1, int row2, int col2) {
        // 添加下面代码：部分测试用例报错 ==》 (row2, col2) 位于第一列，即 col2 = 0时，计算错误
//        if (row1 == 0 && col1 == 0) {
//            return sumDfs(0, 0, total - 1, 0, row2 * n + col2);
//        }
        int res = 0;
        int rowCnt = row2 - row1 + 1;
        // 逐行汇总和
        for (int i = 0; i < rowCnt; i++) {
            int left = (row1 + i) * n + col1;
            int right = (row1 + i) * n + col2;
            res += sumDfs(0, 0, total - 1, left, right);
        }

        return res;
    }

    // 每次查询区间和，包含左右两个端点在内
    private int sumDfs(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int left = treeIndex * 2 + 1;
        int right = treeIndex * 2 + 2;

        int ans;
        if (queryL > mid) {
            ans = sumDfs(right, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            ans = sumDfs(left, l, mid, queryL, queryR);
        } else {
            int lAns = sumDfs(left, l, mid, queryL, mid);
            int rAns = sumDfs(right, mid +1, r, mid + 1, queryR);
            ans = lAns + rAns;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] array = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix q = new NumMatrix(array);

        q.sumRegion(2, 1, 4, 3);

    }
}
