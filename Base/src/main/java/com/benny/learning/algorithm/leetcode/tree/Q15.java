package com.benny.learning.algorithm.leetcode.tree;

import com.benny.learning.utils.TreeNode;

/**
 * https://leetcode-cn.com/problems/merge-two-binary-trees/
 * 617. 合并二叉树
 */
public class Q15 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return root1;
        }
        if (root1 == null && root2 != null) {
            return root2;
        }
        if (root2 == null && root1 != null) {
            return root1;
        }

        int value = root1.val + root2.val;
        TreeNode node = new TreeNode(value);

        node.left = mergeTrees(root1.left, root2.left);
        node.right = mergeTrees(root1.right, root2.right);

        return node;
    }
}


