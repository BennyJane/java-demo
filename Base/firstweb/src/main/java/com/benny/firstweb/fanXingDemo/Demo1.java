package com.benny.firstweb.fanXingDemo;

import java.util.ArrayList;
import java.util.List;

public class Demo1 {
    public static void main(String[] args) {
        funcExc();
    }

    static void funcExc() {
        List arrayList = new ArrayList<>();
        arrayList.add("benny ");
        arrayList.add(100);

        try {
            for (Object o : arrayList) {
                // int 不能通过强制类型转换为String， String.valueOf(item)
                String item = (String) o;
                System.out.println("funcExc " + item);
            }
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
    }

    static void funcExc1() {    // 使用泛型，指定集合内元素类型
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("benny ");
        // 编译阶段就会报错，通过<> 指定集合元素的类型
        // arrayList.add(100);

        try {
            for (String s : arrayList) {
                // int 不能通过强制类型转换为String， String.valueOf(item)
                String item = (String) s;
                System.out.println("funcExc " + item);
            }
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
    }


}
