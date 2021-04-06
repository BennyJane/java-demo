package com.benny.learning.algorithm.offer;

import com.benny.learning.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中和为某一值的路径
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前).
 * <p>
 * 链接：https://www.jianshu.com/p/c3d76ad8e8b3
 */
public class Q15 {

    public static void main(String[] args) {

    }

    private List<List<Integer>> solution1(TreeNode root, int target) {
        List<List<Integer>> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        };
        findPaths(paths, new ArrayList<Integer>(), root, target);
        return paths;
    }

    static void findPaths(List<List<Integer>> paths, List<Integer> path, TreeNode root, int target) {
        

    }
}


