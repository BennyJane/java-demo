package org.example.com.base.tree.segment;

/**
 * 线段树
 */
public class SegmentTree {
    int[] tree;
    int n;

    public SegmentTree(int[] nums) {
        n = nums.length;
        // 存储长度为 4  *  n
        tree = new int[n * 4];
        // 使用数组tree存储树信息，0索引为根节点
        // 左节点索引 = 2 * nodeIndex  +1
        // 右节点索引 = 2 * nodeIndex  + 2
        build(0, 0, n - 1, nums);
    }


    /**
     * 根据数组，初始化线段树
     * 统计每个区间的和
     *
     * @param node 当前节点
     * @param s    区间左索引
     * @param e    区间有索引
     * @param nums 原始数组
     */
    private void build(int node, int s, int e, int[] nums) {
        if (s == e) {
            // 叶子节点：支持存储
            tree[node] = nums[s];
            return;
        }

        // 计算中间索引
        int m = s + (e - s) / 2;
        // 左侧存储[s, m]数据
        build(node * 2 + 1, s, m, nums);
        // 右侧存储[m +1, e]数据
        build(node * 2 + 2, m + 1, e, nums);
        // TODO 节点存储的汇总信息：和、最大值、最小值等
        // 计算node节点处，区间[s ,e]的区间和
        tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2];
    }

    public void update(int index, int val) {
        changeDfs(index, val, 0, 0, n - 1);
    }

    public void changeDfs(int index, int val, int node, int s, int e) {
        if (s == e) {
            tree[node] = val;
            return;
        }

        int m = s + (e - s) / 2;
        if (index <= m) {
            changeDfs(index, val, node * 2 + 1, s, m);
        } else {
            changeDfs(index, val, node * 2 + 2, m + 1, e);
        }

        // 需要更新节点统计信息
        tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2];
    }


    public int sumRange(int left, int right) {
        return rangeDfs(left, right, 0, 0, n - 1);
    }


    /**
     * 查询区间[l, r]上的统计数据
     *
     * @param l
     * @param r
     * @param node
     * @param s
     * @param e
     * @return
     */
    public int rangeDfs(int l, int r, int node, int s, int e) {
        if (l == s && r == e) {
            return tree[node];
        }

        int m = s + (e - s) / 2;
        if (r <= m) {
            // 位于左节点
            return rangeDfs(l, r, node * 2 + 1, s, m);
        } else if (l > m) {
            // 位于右节点
            return rangeDfs(l, r, node * 2 + 2, m + 1, e);
        } else {
            // 横跨左右节点
            return rangeDfs(l, m, node * 2 + 1, s, m) + rangeDfs(m + 1, r, node * 2 + 2, m + 1, e);
        }
    }

}
