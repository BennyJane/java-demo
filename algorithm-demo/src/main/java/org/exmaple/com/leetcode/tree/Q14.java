package org.exmaple.com.leetcode.tree;

import com.benny.learning.utils.TreeNode;

public class Q14 {

    public static void main(String[] args) {

    }

    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(Integer.toString(root.val));

        boolean leftIsEmpty = true;
        if (root.left != null) {
            sb.append("(");
            String leftStr = tree2str(root.left);
            sb.append(leftStr);
            sb.append(")");
            leftIsEmpty = false;
        }

        if (root.right != null) {
            if (leftIsEmpty) {
                sb.append("()");
            }
            sb.append("(");
            String rightStr = tree2str(root.right);
            sb.append(rightStr);
            sb.append(")");
        }
        return sb.toString();
    }


}


