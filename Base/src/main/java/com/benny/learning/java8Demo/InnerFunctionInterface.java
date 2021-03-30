package com.benny.learning.java8Demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * java8 4个内置函数式接口
 */
public class InnerFunctionInterface {
    public static void main(String[] args) {

    }

    //Consumer<T>消费型接口:（无返回值，有一个泛型的输入参数）
    @Test
    public void test1() {
        happy(10000, money -> System.out.println("发了" + money + "元工资，开心啊"));
    }
    //处理一个数据
    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    //供给型接口(无输入参数，有一个泛型的返回值)
    @Test
    public void test2(){
        getNumList(10, () -> (int) (Math.random() * 100)).forEach(System.out::println);
    }
    //获取一个整数集合
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    //Function<T,R>函数型接口（有一个泛型的输入参数和一个泛型的返回值）
    @Test
    public void test3(){
        System.out.println(strHandler("\t\n    " + "  你好啊！！！哈哈   \r\n\t", String::trim));
    }
    //处理字符串后返回
    public String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    //Predict<T> 断言型(判断型)接口：(有一个泛型的输入参数，返回一个bool值)
    @Test
    public void test4(){
        List<String> list = Arrays.asList("Hello","what","is","your","name");
        filterStr(list, s -> s.length() > 3).forEach(System.out::println);
    }
    //将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> strings = new ArrayList<>();
        list.forEach(s -> {
            if (predicate.test(s)) {
                strings.add(s);
            }
        });
        return strings;
    }
}
