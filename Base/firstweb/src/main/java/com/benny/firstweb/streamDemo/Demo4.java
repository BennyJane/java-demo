package com.benny.firstweb.streamDemo;

import com.benny.firstweb.Utils;
import com.sun.deploy.ui.UITextArea;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://juejin.cn/post/6844903844816617485
 * JDK1.8-Stream中常用的API（流操作）
 */
public class Demo4 {
    public Demo4() {
    }

    public static void main(String[] args) {
        Demo4 d = new Demo4();

        d.example1();
        d.example2();
    }


    /**
     * Stream 创建
     */
    public void example1() {
        // Stream.generate方法
        // 最好使用limit指定生成数量，默认为无限个，
        Stream<Integer> generate = Stream.generate(() -> 1);
        // generate 只能被forEach循环遍历一次，再次调用会报错
        // stream has already been operated upon or close
        generate.limit(2).forEach(x -> Utils.print(x));
        // generate.forEach(x -> Utils.print(x));

        // Stream.iterate
        // 生成的序列也是无穷
        Stream<Integer> iterate = Stream.iterate(10, x -> x + 1);
        //iterate.forEach(x -> Utils.print(x));
        iterate.limit(10).forEach(x -> Utils.print(x));

        // Stream.of()
        String[] str = {"a", "b", "c"};
        Stream<String> str1 = Stream.of(str);

        // String -> Stream
        String str2 = "abc";
        IntStream chars = str2.chars();
        chars.forEach(x -> Utils.print(x));
    }

    /**
     * 中间操作：
     * filter
     * distinct
     * sorted
     * limit
     * skip
     * map
     * flatMap
     * peek
     * collect
     */
    public void example2() {
        // filter
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> collect = integerList.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());
        Utils.print(collect, "[filter]: ");

        // distinct: 依据所生成元素的 hasCode 和 equals
        List<Integer> collect1 = integerList.stream()
                .distinct().collect(Collectors.toList());
        Utils.print(collect1, "[distinct]: ");

        // sorted
        
    }
}
