package com.benny.learning.utils;

public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.val = value;
    }

    public void addLeft(TreeNode node) {
        this.left = node;
    }

    public void addRight(TreeNode node) {
        this.right = node;
    }
}
