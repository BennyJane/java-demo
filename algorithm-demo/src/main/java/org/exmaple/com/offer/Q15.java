package org.exmaple.com.offer;

import org.exmaple.com.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树中和为某一值的路径
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前).
 * <p>
 * 链接：https://www.jianshu.com/p/c3d76ad8e8b3
 * https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/solution/mian-shi-ti-34-er-cha-shu-zhong-he-wei-mou-yi-zh-5/
 *
 * 本题路径定义：从树的 根节点开始，往下一直到 叶节点 所经过的节点形成一条路径。
 *
 */
public class Q15 {
    // 解法2:：定义参数,作为全局变量
    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    // 变体:：找出二叉树所有的路径
    LinkedList<List<Integer>> res1 = new LinkedList<>();
    LinkedList<Integer> path1 = new LinkedList<>();


    public static void main(String[] args) {
        TreeNode header = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(12);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(11);

        header.addLeft(node1);
        header.addRight(node2);
        node1.addLeft(node3);
        node1.addRight(node4);
        node3.addLeft(node5);
        node3.addRight(node6);

        List<List<Integer>> paths;
        paths = solution1(header, 22);
        System.out.println(paths);

        Q15 q = new Q15();
        System.out.println(q.solution2(header, 22));

        System.out.println(q.solution3(header));
    }

    private static List<List<Integer>> solution1(TreeNode root, int target) {
        List<List<Integer>> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        findPaths(paths, new ArrayList<Integer>(), root, target);
        return paths;
    }

    static void findPaths(List<List<Integer>> paths, List<Integer> path, TreeNode root, int target) {
        if (root == null) return;
        path.add(root.val);
        if (root.val == target && root.left == null && root.right == null) {
            paths.add(path);
        }
        // 每遇到一个分支，就单独创建一个path，用于存储
        // 浪费存储空间
        List<Integer> path2 = new ArrayList<Integer>();
        path2.addAll(path);
        findPaths(paths, path, root.left, target - root.val);
        findPaths(paths, path2, root.right, target - root.val);
    }

    private List<List<Integer>> solution2(TreeNode root, int target) {
        recur(root, target);
        return res;
    }

    private void recur(TreeNode root, int tar) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        tar -= root.val;
        // 每个节点的值，被添加到路径中后，需要进行判断
        // 判断是否sum满足；该节点是否已经是叶节点；
        if (tar ==0 && root.left == null &&root.right == null){
            // 必须重新生成新的列表
            res.add(new LinkedList(path));
        }
        recur(root.left, tar);
        recur(root.right, tar);
        path.removeLast();  // 回溯法，删除当前处理的节点
    }


    /**
     * 找出所有路径
     */
    private List<List<Integer>> solution3(TreeNode root) {
        findAllPaths(root);
        return res1;
    }

    private void findAllPaths(TreeNode root) {
        if (root == null) return;
        path1.add(root.val);
        if (root.left == null && root.right == null) {
            res1.add(new LinkedList(path1));
        }
        findAllPaths(root.left);
        findAllPaths(root.right);
        path1.removeLast();
    }


}


