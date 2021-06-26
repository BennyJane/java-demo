package com.benny.learning.algorithm.leetcode.array.simple;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.benny.learning.utils.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 653. 两数之和 IV - 输入 BST
 * https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
 */
public class Q23 {
    // 使用HashSet，递归（深度搜索优先）
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return false;
    }

    private boolean find(TreeNode root, int k, Set<Integer> set) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return find(root.left, k, set) || find(root.right, k, set);
    }

    // 广度优先搜索
    public boolean findTarget2(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            if (queue.peek() != null) {
                TreeNode node = queue.remove();
                if (set.contains(k - node.val)) {
                    return true;
                }
                set.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            } else {
                queue.remove();
            }
        }
        return false;
    }

}


