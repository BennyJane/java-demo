package com.benny.firstweb.reflectDemo;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class child = null;
        try {
            // forName 根据类的路径获取类对象
            child = Class.forName("com.benny.firstweb.reflectDemo.ReflectChild");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("-------------------------------------------------------------------");
        // 获取对象的所有共有属性
        Field[] fields = child.getFields();
        printInfo(Arrays.asList(fields));

        System.out.println("-------------------------------------------------------------------");
        // 获取对象的所有属性(共有 + 私有)， 不含继承的
        Field[] fields1 = child.getDeclaredFields();
        printInfo(Arrays.asList(fields1));

        System.out.println("-------------------------------------------------------------------");
        // 获取对象的所有共有方法(包含继承的父类)
        Method[] methods = child.getMethods();
        printInfo(Arrays.asList(methods));

        System.out.println("-------------------------------------------------------------------");
        // 获取对象的所有方法(不包含继承的父类)
        Method[] declaredMethods = child.getDeclaredMethods();
        printInfo(Arrays.asList(declaredMethods));


        System.out.println("-------------------------------------------------------------------");
        // 获取对象的所有共有的构造方法(包含继承的父类)
        Constructor[] constructors = child.getConstructors();
        printInfo(Arrays.asList(constructors));

        System.out.println("-------------------------------------------------------------------");
        // 获取对象的所有方法(不包含继承的父类)
        Constructor[] declaredConstructors = child.getDeclaredConstructors();
        printInfo(Arrays.asList(declaredConstructors));

        System.out.println("-------------------------------------------------------------------");
        // 获取对象的所有方法(不包含继承的父类)
        try {
            Class c = Class.forName("com.benny.firstweb.reflectDemo.ReflectChild");
            ReflectChild r = (ReflectChild) c.newInstance();    // 调用默认构造方法，获取实例
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        // 获取指定构造方法
        Class ch = Class.forName("com.benny.firstweb.reflectDemo.ReflectChild");
        try {
            Constructor constructor = ch.getConstructor(String.class, String.class);
            ReflectChild child1 = (ReflectChild) constructor.newInstance("1", "2");

            // 获取方法，并执行
            Method method = ch.getMethod("funcExc3");
            method.invoke(child1);  // 需要传入 实例对象
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    static <T> void printInfo(List<T> data) {
        for (T item : data) {
            System.out.println(item);
        }
    }
}
