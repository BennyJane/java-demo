package com.benny.firstweb.enumDemo;

// 静态导入所有枚举类元素，可直接使用
import static com.benny.firstweb.enumDemo.EnumDemo.Attr1;

public class Demo {
    public static void main(String[] args) {
        EnumDemo example = Attr1;
        System.out.println("枚举类： " + example);

        System.out.println(EnumDemo.values());
        // values() 获取枚举类中定义的值，有序
        // 枚举类元素 ordinal()方法，获取元素的顺序符号
        // 枚举类元素 toString()方法，获取元素的名称
        for (EnumDemo e : EnumDemo.values()) {
            System.out.println(e +", ordinal " + e.ordinal());
            System.out.println(e.toString());
        }

        System.out.println(Attr1);

    }
}
