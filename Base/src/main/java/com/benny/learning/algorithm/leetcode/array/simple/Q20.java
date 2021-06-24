package com.benny.learning.algorithm.leetcode.array.simple;

import javax.security.auth.login.AccountExpiredException;
import java.util.ArrayList;
import java.util.List;

/**
 *119. 杨辉三角 II
 * https://leetcode-cn.com/problems/pascals-triangle-ii/
 */
public class Q20 {
    // 只用一个数组完成；倒序计算
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);    // 先添加第一行
        for (int i = 1; i <= rowIndex; i++) {   // 每行的长度为行号+1，从0开始编号
            list.add(0);    // 新的一行，相对于上一行，长度+1
            for (int j = i; j > 0; j--) {   // 每行末尾索引为行号
                list.set(j, list.get(j) + list.get(j - 1));
            }
        }

        return list;
    }
}


