package org.example.com.leetcode.Daily.Mar;

import org.example.com.utils.TreeNode;

import java.util.*;

// 606.根据二叉树创建字符串
public class Q19 {
    // 这个写法：执行速度是最快的
    private StringBuilder ans;

    public String tree2str1(TreeNode root) {
        ans = new StringBuilder();
        dfs(root);
        return ans.toString();
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        ans.append(node.val);
        if (node.left == null && node.right == null) {
            return;
        }
        // 至少有一个子节点不为null
        if (node.left == null) {    // right节点不为null
            ans.append("()");
            ans.append("(");
            dfs(node.right);
            ans.append(")");
        } else {
            ans.append("(");
            dfs(node.left);
            ans.append(")");
            // 需要考虑right节点是否为null
            if (node.right != null) {
                ans.append("(");
                dfs(node.right);
                ans.append(")");
            }
        }
    }

    public String tree2str2(TreeNode root) {
        if (root == null) {
            return "";
        }
        if (root.left == null && root.right == null) {
            return String.valueOf(root.val);
        }
        if (root.right == null) {
            return String.format("%d(%s)", root.val, tree2str2(root.left));
        }
        // FIXME 效率低于 StringBuilder
        return String.format("%d(%s)(%s)", root.val, tree2str2(root.left), tree2str2(root.right));
    }

    // TODO 官方题解:
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        if (root.left == null && root.right == null) {
            return Integer.toString(root.val);
        }
        if (root.right == null) {
            return new StringBuilder().append(root.val).append("(").append(tree2str(root.left)).append(")").toString();
        }
        return new StringBuilder().append(root.val).append("(").append(tree2str(root.left)).append(")(").append(tree2str(root.right)).append(")").toString();
    }

    // 迭代法
    public String tree2str3(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder res = new StringBuilder();
        Deque<TreeNode> stack = new LinkedList();
        stack.add(root);
        Set<TreeNode> seen = new HashSet<>();

        while (!stack.isEmpty()) {
            TreeNode node = stack.peekLast();
            // 等效判断： !seen.add(node)
            if (seen.contains(node)) {
                if (node != root) {
                    res.append(")");
                }
                stack.pollLast();
            } else {
                seen.add(node);
                if (node != root) {
                    res.append("(");
                }
                res.append(node.val);
                if (node.left == null && node.right != null) {
                    res.append("()");
                }
                if (node.right != null) {
                    stack.addLast(node.right);
                }
                if (node.left != null) {
                    stack.addLast(node.left);
                }
            }
        }

        return res.toString();
    }

    public String tree2str4(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        // 模拟栈：末尾作为栈顶， add peekLast pollLast
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.add(root);   // 插入尾部 ==》 addLast
        // 记录节点是否已经访问：当第二次访问时，添加右括号
        Set<TreeNode> visited = new HashSet<>();
        while (!stack.isEmpty()) {
            TreeNode topNode = stack.peekLast();
            // TODO 直接使用Set.add方法返回值，判断是否已经访问过，
            if (!visited.add(topNode)) {
                sb.append(")");
                stack.pollLast();
            } else {
                // 此处，不需要更新visited内数
                sb.append("(");
                sb.append(topNode.val);
                if (topNode.left == null && topNode.right != null) {
                    sb.append("()");
                }
                if (topNode.right != null) {
                    stack.addLast(topNode.right);
                }
                if (topNode.left != null) {
                    stack.addLast(topNode.left);
                }
            }
        }

        // 删除首尾括号
        return sb.toString().substring(1, sb.length()-1);
    }

}
