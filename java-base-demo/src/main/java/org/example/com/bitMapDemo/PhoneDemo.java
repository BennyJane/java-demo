package org.example.com.bitMapDemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//已知某个文件内包含一些电话号码，每个号码为8位数字，统计不同号码的个数
public class PhoneDemo {
    public static void main(String[] args) {
        int maxValue = 99999999;
        String[] phones = {
                "00121245",
                "52546524",
                "14526445",
                "11521115",
                "15641852",
                "15125142",
                "15654424",
                "02561225",
                "00121245"
        };


        int[] bitMap = new int[maxValue / 32 + 1];

        int total = phones.length;
        System.out.println("输入电话号码总数： " + total);
        Set<Integer> set = new HashSet<>();
        for (String p : phones) {
            int v = Integer.parseInt(p);
            int index = v >> 5;
            int offset = v & 31;

            int old = bitMap[index];
            bitMap[index] = bitMap[index] | (1 << offset);
            if (old == bitMap[index]) {
                System.out.println("重复值: " + v);
                total--;
            }
            set.add(v);
        }
        System.out.println(set);
        System.out.println("不重复电话号的数量： " + total);

        // 重新遍历bitMap统计不重复电话号码
        List uniquePhone = new ArrayList<>();
        for (int i = 0; i < bitMap.length; i++) {
            int b = bitMap[i];
            if (b == 0) continue;
            for (int j = 0; j < 32; j++) {
                if (((b >> j) & 1) == 1) {
                    int num = 32 * i + j;
                    String phone = String.format("%08d", num);
                    uniquePhone.add(phone);
                }
            }
        }
        System.out.println(uniquePhone);
    }
}
