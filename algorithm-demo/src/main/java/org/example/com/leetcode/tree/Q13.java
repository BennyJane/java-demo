package org.example.com.leetcode.tree;


import org.example.com.utils.MultiNode;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 * 589. N叉树的前序遍历
 * 给定一个N叉树，返回其节点值的前序遍历，
 * N叉树在输入中按层序遍历进行序列化表示，每组子节点由空值null分隔。
 */
public class Q13 {
    List<Integer> result;

    public List<Integer> preOrder(MultiNode root) {
        dfs(root);
        return result;
    }

    public void dfs(MultiNode node) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        if (node.children.isEmpty()) {
            for (MultiNode item : node.children) {
                dfs(item);
            }
        }
    }



}


