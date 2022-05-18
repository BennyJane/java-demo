package org.example.com.utils;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet();


        treeSet.add(4);
        treeSet.add(0);
        treeSet.add(5);
        treeSet.add(1);
        treeSet.add(7);
        treeSet.add(3);
        treeSet.add(2);
        treeSet.add(6);

        int size = treeSet.size();
        for (int i = 0; i < size; i++) {
            // FIXME 不能通过索引访问
        }
        Iterator<Integer> it = treeSet.iterator();
        System.out.println(treeSet.first());
        System.out.println(treeSet.last());
        System.out.println(treeSet.isEmpty());
    }
}
