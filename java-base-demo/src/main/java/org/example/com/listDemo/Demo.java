package org.example.com.listDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 数组转List
 * https://blog.csdn.net/x541211190/article/details/79597236
 */
public class Demo {
    public static void main(String[] args) {
        firstEx();

    }

    static void firstEx() {
        /**
         * List list = Arrays.asList(strArray)
         * 数组转List后，只能查，改； 不能增、删，否则抛出异常。
         * 原因：生成的类型为java.util.Arrays.ArrayList类, 只定义了 set get contains;
         *      没有定义add remove 方法
         */
        String[] l = new String[2];
        List list = Arrays.asList(l);
        // list.add("1");  // 异常：UnsupportedOperationException
        System.out.println(list);
    }

    static void secondEx() {
        /**
         * ArrayList<String> list = new ArrayList<String>(Arrays.asList(l));
         * ArrayList 将asList的结果转化为 java.util.ArrayList
         * 支持增删查改操作， 在list数据量不大情况下使用
         */
        String[] l = new String[2];
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(l));
        list.add("1");
        System.out.println(list);
    }

    static void thirdEx() {
        /**
         * Collections.addAll(arrayList, strArray)方式转换，根据数组的长度创建等长List，
         * 通过Collections.addAll()方法，将数组中元素转化为二进制，然后添加到list中
         */
        String[] l = new String[2];
        ArrayList<String> list = new ArrayList<>(l.length);
        Collections.addAll(list, l);
        list.add("1");
        System.out.println(list);
    }

    static void fourEx() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(74);
        list.add(3);
        list.add(2);
        System.out.println(list.contains(10));
        System.out.println(list.indexOf(2));
    }
}
