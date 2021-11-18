package org.exmaple.com.leetcode.tree;

import com.benny.learning.utils.TreeNode;

/**
 * https://leetcode-cn.com/problems/subtree-of-another-tree/
 * 572. 另一个树的子树
 * 给定两个非空二叉树s和t，检验s中是否包含和t具有相同结构和节点值的子树；
 * s的一个子树包括s的一个节点和这个节点的所有子孙，s也可以看做自身的一颗子树
 */
public class Q12 {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return dfs(s, t);
    }

    // 两个递归调用
    public boolean dfs(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        return check(s, t) || dfs(s.left, t) || dfs(s.right, t);
    }

    // 检测两个树是否相同
    public boolean check(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        // s、t至少有一个不为null
        if (s == null || t == null || s.val != t.val) {
            return false;
        }
        return check(s.left, t.left) && check(s.right, t.right);
    }


    public static void main(String[] args) {

    }
}


