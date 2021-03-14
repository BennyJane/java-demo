package com.benny.firstweb.baseDemo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForDemo {
    public static void main(String[] args) {
        // 集合
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // for
        for (int out : list) {
            System.out.print(out + ", ");
        }
        System.out.println("\n-------------------------------");

        // forEach
        // lambda 匿名函数
        list.forEach(out -> System.out.print(out + ", "));
        System.out.println("\n-------------------------------");

        // 箭头函数
        list.forEach(item -> {
            if (item != 1) {
                System.out.print(item + ", ");
            }
        });
        System.out.println("\n-------------------------------");

        // 字典MAP
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);
        map.put("D", 40);
        map.put("E", 50);
        map.put("F", 60);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey() + " : " + entry.getValue() + " ");
        }
        System.out.println("\n-------------------------------");

        // forEach
        map.forEach((k, v) -> System.out.print(k + " : " + v + " "));
        System.out.println("\n-------------------------------");

        map.forEach((k, v) -> {
            System.out.print("Map: " + k + " : " + v + " ");
            if ("E".equals(k)) {
                System.out.println("Hello E");
            }
        });
    }
}
