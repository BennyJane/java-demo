package org.example.com.utils;

import java.util.List;

public class MultiNode {
    public int val;
    public List<MultiNode> children;

    public MultiNode() {
    }

    public MultiNode(int val) {
        this.val = val;
    }

    public MultiNode(int val, List<MultiNode> children) {
        this.val = val;
        this.children = children;
    }
}
