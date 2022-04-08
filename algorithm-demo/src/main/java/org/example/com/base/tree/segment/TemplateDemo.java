package org.example.com.base.tree.segment;

public class TemplateDemo<E> {
    // 存储原始数据
    private E[] data;
    private int n; // 输入数组长度
    // 存储线段树
    private E[] tree;
    // 每个非叶子节点的统计逻辑
    // 接口必须添加泛型
    private Merger<E> merger;


    public TemplateDemo(E[] array, Merger merger) {
        this.merger = merger;
        this.n = array.length;
        // 重新存储输入数组
        // 更新数组时，避免更新传入的数组
        data = (E[]) new Object[n];
        for (int i = 0; i < n; i++) {
            data[i] = array[i];
        }
        // 构建线段树
        tree = (E[]) new Object[n * 4];
        // 初始化线段树
        buildSegmentTree(0, 0, n - 1);
    }

    /**
     * 构建线段树:以treeIndex作为根节点，表示区间[l, r]的线段树
     * 在递归中，treeIndex表示当前树节点对应索引
     *
     * @param treeIndex 根节点索引
     * @param l         区间左端点
     * @param r         区间右端点, 可以取到
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 递归终止条件：区间只有一个值，即到达叶子节点
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        // 根据满二叉树的数组表示规则：计算左右两个节点索引位置
        int leftTreeIndex = treeIndex * 2 + 1;
        int rightTreeIndex = treeIndex * 2 + 2;

        // 使用中点，划分左右节点区间范围
        int mid = l + (r - l) / 2;

        // 递归计算左、右子树的统计值
        // FIXME 全局统一：将mid归入左子树
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        // 根据左右节点的值，汇总当前节点(父节点)的值
        tree[treeIndex] = this.merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 查询某个区间的统计值
     *
     * @param queryL 区间左索引
     * @param queryR 区间右索引
     * @return
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryR >= n) {
            throw new IllegalArgumentException("Index is illegal");
        }
        // 递归函数
        return queryTreeDfs(0, 0, n - 1, queryL, queryR);
    }

    /**
     * 实际执行查询的逻辑
     * 初始执行入口，即线段树根节点 0 , 区间[0, n-1]
     *
     * @param treeIndex 查询线段树当前节点索引
     * @param l         线段树当前节点区间左索引
     * @param r         线段树当前节点区间右索引
     * @param queryL    查询区间左索引
     * @param queryR    查询区间右索引
     * @return
     */
    private E queryTreeDfs(int treeIndex, int l, int r, int queryL, int queryR) {
        // 退出循环：查询区间 == 当前节点区间
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = 2 * treeIndex + 1;
        int rightTreeIndex = 2 * treeIndex + 2;

        // 需要考虑查询区间分布的三种情况
        E ans = null;
        if (queryL > mid) {
            // 在线段树的右子树上
            ans = queryTreeDfs(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            // 在线段树的左子树上
            // FIXME 全局统一：将mid归入左子树
            ans = queryTreeDfs(leftTreeIndex, l, mid, queryL, queryR);
        } else {
            // 当前区间横跨当前节点
            E leftAns = queryTreeDfs(leftTreeIndex, queryL, mid, queryL, mid);
            E rightAns = queryTreeDfs(rightTreeIndex, mid + 1, queryR, queryL, mid);
            ans = this.merger.merge(leftAns, rightAns);
        }

        return ans;
    }


    public void update(int index, E e) {
        if (index < 0 || index >= n) {
            throw new IllegalArgumentException("Index is illegal");
        }
        data[index] = e;
        updateTreeDfs(0, 0, n - 1, index, e);
    }

    /**
     * @param treeIndex
     * @param l
     * @param r
     * @param index
     * @param e
     */
    public void updateTreeDfs(int treeIndex, int l, int r, int index, E e) {
        // 终止条件：查询到叶子节点
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = treeIndex * 2 + 1;
        int rightTreeIndex = treeIndex * 2 + 2;

        if (index > mid) {
            updateTreeDfs(rightTreeIndex, mid + 1, r, index, e);
        } else {
            // index <= mid
            // FIXME 全局统一：将mid归入左子树
            updateTreeDfs(leftTreeIndex, l, mid, index, e);
        }
        // 更新当前节点的值
        // TODO 回溯更新相关节点的统计值
        tree[treeIndex] = this.merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    public static void main(String[] args) {
        // 求区间的最大值的线段树
        Integer[] nums = new Integer[]{34, 65, 8, 10, 21, 86, 79, 30};
        Merger<Integer> func = new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return Math.max(a, b);
            }
        };

        TemplateDemo segTree = new TemplateDemo<Integer>(nums, func);

        System.out.println(segTree.query(4, 7));
    }

}
