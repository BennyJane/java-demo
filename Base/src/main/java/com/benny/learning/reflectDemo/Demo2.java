package com.benny.learning.reflectDemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Demo2 {
    private Field f1;

    // 内部类
    class FieldSpy<T> {
        public boolean[][] b = {{false, false}, {false, false}};
        public String name = "Benny";
        public List<Integer> list;
        public T val;
    }


    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        // 获取原始类
        // getClass()
        TestClass d = new TestClass("wuhan");
        System.out.println("getClass() : " + d.getClass());

        System.out.println("-------------------------------------------");
        // .class
        Class<?> cls = TestClass.class;
        System.out.println("Class.class : " + cls);
        System.out.println(".getName : " + cls.getName());
        System.out.println(".getCanonicalName : " + cls.getCanonicalName());
        System.out.println(".getTypeName : " + cls.getTypeName());
        System.out.println(".getSimpleName : " + cls.getSimpleName());
        System.out.println(".getClasses : " + cls.getClasses());

        System.out.println("-------------------------------------------");
        // .getFiled()
        // 需要捕捉异常
        Field f1 = FieldSpy.class.getField("b");    // 获取共有类成员
        System.out.format("Type: %s%n ", f1.getType());
        System.out.format("f1 %s ", f1);

        Field f2 = FieldSpy.class.getField("name");
        System.out.format("Type  %s%n", f2.getType());

        Field f3 = FieldSpy.class.getField("list");
        System.out.format("Type  %s%n", f3.getType());

        Field f4 = FieldSpy.class.getField("val");
        System.out.format("Type %s%n", f4.getType());


        System.out.println("-------------------------------------------");
        // Method 方法
        // .getDeclaredMethods()
        Method[] methods1 = System.class.getDeclaredMethods();
        System.out.println("System getDeclaredMethods 清单 （数量 = " + methods1.length + " ) : " );
        for (Method m : methods1) {
            System.out.println(m);
        }

        // .getMethods()
        Method[] methods2 = System.class.getMethods();
        System.out.println("System getDeclaredMethods 清单 （数量 = " + methods2.length + " ) : " );
        for (Method m : methods2) {
            System.out.println(m);
        }

        // .getMethod() 获取指定方法
        Method method = System.class.getMethod("currentTimeMillis");
        System.out.println(method);
        System.out.println(method.invoke(null));    // invoke() 调用反射获取的方法

        System.out.println("-------------------------------------------");
        // constructor 构造方法
        Constructor<?>[] constructors = String.class.getConstructors(); // 返回类的所有public构造方法
        Constructor<?>[] constructors1 = String.class.getDeclaredConstructors();    // 返回类的所有构造方法
        Constructor constructor = String.class.getConstructor(String.class);
        Constructor constructor2 = String.class.getConstructor(String.class);
        Constructor constructor3 = String.class.getConstructor(String.class);
        Constructor constructor4 = String.class.getDeclaredConstructor(String.class);

        String str = (String) constructor.newInstance("bbb");


    }
}
