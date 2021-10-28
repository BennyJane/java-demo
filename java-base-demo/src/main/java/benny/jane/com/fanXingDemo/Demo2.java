package benny.jane.com.fanXingDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 上界通配符： <? extends ClassType> 通配符?代表ClassType的所有子类型，限制传入类型为指定类的子类
 * 下界通配符： <? super ClassType> 通配符代表 ClassType的所有超类，传入类型都是ClassType的父类
 */
public class Demo2 {
    // 方法使用泛型：标记入参 和 返回值参数类型
    public <T> void func(T x) {
        System.out.println(x.getClass().getName() + " " + x);
    }

    // 泛型通配符： ？
    // 表示可以匹配任意类型
    public static void generic(List<?> data) {
        System.out.println("get one data: " +  data.get(0));
    }

    public static void main(String[] args) {
        List<String> name = new ArrayList<String>();
        List<Integer> age = new ArrayList<Integer>();
        List<Number> number = new ArrayList<Number>();

        name.add("benny");
        age.add(27);
        number.add(10000);
        generic(name);
        generic(age);
        generic(age);
    }
}
