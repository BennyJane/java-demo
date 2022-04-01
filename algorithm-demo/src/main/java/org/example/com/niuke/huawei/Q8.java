package org.example.com.niuke.huawei;

import java.util.*;

//HJ41 称砝码
//LC: 多个数的和
public class Q8 {

    private static Set<Integer> set = new HashSet<>();

    // FIXME 递归超时
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 此处必须使用 hasNextInt() 方法
        while (sc.hasNextInt()) {
            // 处理连续多行数据输入
            int n = sc.nextInt();
            int[] mArr = new int[n];
            int[] xArr = new int[n];
            for (int i = 0; i < n; i++) {
                mArr[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                xArr[i] = sc.nextInt();
            }
            set.clear();
            set.add(0);
            dfs(mArr, xArr, 0, 0);

            System.out.println(set.size());
        }
    }

    private static void dfs(int[] m, int[] count, int index, int res) {
        // 必须放在这里添加
        set.add(res);
        int size = m.length;
        if (index >= size) {
            return;
        }

        // 处理重复数据
        for (int i = 0; i <= count[index]; i++) {
            int cur = res + m[index] * i;
            dfs(m, count, index + 1, cur);
        }
    }


}

/**
10
2000 1999 1998 1997 1996 1995 1994 1993 1992 1991
10 10 10 10 10 10 10 10 10 10
16601
 */