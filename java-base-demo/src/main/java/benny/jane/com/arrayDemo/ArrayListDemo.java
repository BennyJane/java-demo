package benny.jane.com.arrayDemo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * ArrayList 可动态修改的数组，长度不定，可添加或删除元素
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        method();
        System.out.println("\n [method2]: \n");
        method2();
    }

    private static void method() {
        // ArrayList<Obj> Obj只能为引用数据类型
        ArrayList<Integer> nums = new ArrayList<>();

        // 添加元素
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);

        System.out.println(nums);

        // 获取元素: 索引从0开始
        System.out.println(nums.get(1));

        // 修改元素
        nums.set(0, 5);
        System.out.println(nums);

        // 删除元素
        nums.remove(0);
        System.out.println(nums);

        System.out.println("size: " + nums.size());

        // 迭代
        nums.forEach(n -> System.out.print(n + ", "));
        System.out.println();

        // 排序
        nums.add(1);
        Collections.sort(nums);
        System.out.println(nums);
    }

    private static void method2() {
        int len = 5;
        int col = 10;
        // 数组类型： ArrayList<Integer>
        ArrayList<Integer>[] array = new ArrayList[len];
        for (int i = 0; i < len; i++) {
            array[i] = new ArrayList<>(col);
        }

        System.out.println(array);
    }
}
