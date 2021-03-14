package com.benny.firstweb.streamDemo;

import com.benny.firstweb.Utils;
import com.sun.deploy.ui.UITextArea;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReferenceArray;
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

//        d.example1();
//        d.example2();
        d.example3();
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
        // Integer 自身实现Comparable接口，可以直接使用排序规则
        List<Integer> integers = Arrays.asList(5, 8, 2, 6, 41, 11);
        // 默认升序排列
        List<Integer> sorted = integers.stream().sorted().collect(Collectors.toList());
        List<Integer> reversedOrder = integers.stream()
                .sorted(Comparator.reverseOrder())  // TODO
                .collect(Collectors.toList());
        // 通过Comparator.comparing()实现自定义排序方式
        List<Integer> ages = integers.stream().sorted(Comparator.comparing(v -> v.hashCode()))
                .collect(Collectors.toList());

        // limit: 返回一个不超过给定长度的流
        // skip: 舍弃
        List<Integer> integers1 = Arrays.asList(1, 2, 3, 42, 5, 63, 5, 2);
        List<Integer> collect2 = integers1.stream().limit(3).collect(Collectors.toList());
        List<Integer> collect3 = integers1.stream().skip(3).collect(Collectors.toList());

        // map： 归纳
        // flatMap: 扁平化，将流中的数组或集合元素展开，或者 减少一个层级
        String[] words = {"hello", "world"};
        List<String> collect4 = Stream.of(words)
                .map(w -> w.split(""))
                //方法调用将两个String[]扁平化为一个stream
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        // peek 在流的每个元素恢复运行之前，插入执行一个动作
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        List<Integer> result =
                numbers.stream()
                        .peek(x -> System.out.println("from stream: " + x))
                        .map(x -> x + 17)
                        .peek(x -> System.out.println("after map: " + x))
                        .filter(x -> x % 2 == 0)
                        .peek(x -> System.out.println("after filter: " + x))
                        .limit(3)
                        .peek(x -> System.out.println("after limit: " + x))
                        .collect(Collectors.toList());

        // collect 收集
        // Collectors.toList() Collectors.toSet() Collectors.toMap() 需要传参，有三种重载方法


    }

    /**
     * 中止操作
     * 循环： forEach
     * 计算： min max count average
     * 匹配： anyMatch allMatch noneMatch findFirst findAny
     * 汇聚： reduce
     * 收集器: collect
     */
    public void example3() {
        // 三者都返回boolean类型的结果，都是短路判断
        // anyMatch 检测流中是否有一个满足判断的数据
        // allMatch 检测流中元素是否全部满足判断条件
        // noneMatch 检测流中所有元素是否都不满足判断条件


        // findAny 返回当前流中任意元素（只返回了一个）
        // findFirst 返回第一个元素
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8);
        Optional<Integer> any = integerList.stream().filter(v -> v > 5).findAny();
        System.out.println("any = " + any);

        Optional<Integer> first = integerList.stream().filter(v -> v > 5).findFirst();
        System.out.println("first = " + first);

        // reduce 归约、连续处理
        Integer reduceSum = integerList.stream().reduce(0, (a, b) -> a + b);
        System.out.println("reduceSum " + reduceSum);
        int reduceSum1 = integerList.stream().reduce(0, Integer::sum);
        System.out.println("reduceSum1 " + reduceSum1);

        // 最大值||最小值
        List<Integer> integers = Arrays.asList(1, 2, 3, 6, 8);
        Optional<Integer> min = integers.stream().reduce(Integer::min);
        System.out.println("min = " + min);
        Optional<Integer> max = integers.stream().reduce(Integer::max);
        System.out.println("max = " + max);

        // 收集器 Collectors
        // Collectors.maxBy Collectors.minBy
        // Collectors.summingInt
        // Collectors.averagingInt
        // Collectors.joining
        // Collectors.counting
        String collect = integerList.stream().map(v -> v.toString()).collect(Collectors.joining(", "));
        System.out.println("joining = " + collect);

        // 分组
        // Collectors.groupingBy
        // 多级分组

    }
}
