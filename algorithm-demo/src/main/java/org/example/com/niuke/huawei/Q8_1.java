package org.example.com.niuke.huawei;

import java.util.*;

//HJ41 称砝码
public class Q8_1 {

    // 广度优先搜索
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] mArr = new int[n];
            int[] cntArr = new int[n];
            for (int i = 0; i < n; i++) {
                mArr[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                cntArr[i] = sc.nextInt();
            }
            Set<Integer> set = new HashSet<>();
            // 添加初始值： 0
            set.add(0);

            for (int i = 0; i < n; i++) {
                List<Integer> pre = new ArrayList<>(set);
                // j = 0 即不改变set的值，因此可以忽略
                for (int j = 1; j < cntArr[i]; j++) {
                    // 遍历当前存在的和
                    for (int k = 0; k < pre.size(); k++) {
                        set.add(pre.get(k) + mArr[i] * j);
                    }
                }
            }

            System.out.println(set.size());
        }
    }

}
