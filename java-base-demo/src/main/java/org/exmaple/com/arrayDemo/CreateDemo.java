package org.exmaple.com.arrayDemo;

/**
 * https://blog.csdn.net/u014199097/article/details/50551731
 */
public class CreateDemo {
    public static void main(String[] args) {

    }

    /**
     * 声明
     */
    static void methodTest() {
        // 一位数组
        int[] intArray;
        int intArray1[];
        // 声明浮点型数组
        float floatArray0[];
        float[] floatArray1;
        // 声明布尔型数组
        boolean boolArray0[];
        boolean[] boolArray1;
        // 声明字符型数组
        char charArray0[];
        char[] charArray1;
        // 声明字符串数组
        String stringArray0[];
        String[] stringArray1;
        // 错误的声明数组的方式，声明数组的时候不能指定其大小
        // int [5] intErrorArray0;
        // int intErrorArray1[5];
    }

    /**
     * 创建
     */
    static void method2Test() {
        // 创建数组，如果在创建的同时不初始化数组则必须指定其大小
        int[] intArray0 = new int[3];
        // 错误的创建数组的方式，如果创建数组时不指定大小则必须初始化
        // intArray1 = new int[];
        // 创建数组时，不指定数组大小则必须在创建的同时初始化数组
        int[] intArray1 = new int[]{0, 1, 2};
        //使用new创建数组对象但是分配数组时会自动为数组分配默认值，具体如下：
        System.out.println("intArray0[0]=" + intArray0[0]);
        float[] floatArray0 = new float[3];
        System.out.println("floatArray0[0]=" + floatArray0[0]);
        boolean[] boolArray0 = new boolean[3];
        System.out.println("boolArray0[0]=" + boolArray0[0]);
        char[] charArray0 = new char[3];
        System.out.println("charArray0[0]=" + charArray0[0]);
        String[] stringArray0 = new String[3];
        System.out.println("stringArray0[0]=" + stringArray0[0]);
    }

    static void method3Test() {
        // 静态初始化
        int intArray2[] = new int[]{20, 21, 22};
        // 静态初始化简化方式
        int intArray3[] = {30, 31, 32};
        // 动态初始化
        int[] intArray4 = new int[3];
        // 错误写法：静态初始化不能指定元素个数
        // int intErrorArray5[] = new int[3]{50,51,52};
        // 错误写法：动态初始化必须指定元素个数
        // int intErrorArray6[] = new int[];
    }
}
