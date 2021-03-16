package com.benny.learning.streamDemo;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/**
 * @description java8使用并行流parallelStream以及普通迭代, 并行流，普通流之间的效率对比
 */
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=========================================================");
        streamDemo(10);
        System.out.println("=========================================================");
        streamDemo(1000);
        System.out.println("=========================================================");
        streamDemo(10000);
        System.out.println("=========================================================");
        streamDemo(1000000);


        // 总结：使用lambda之后，运行效率变低了 ，
        // 1万条记录以下的时候 普通迭代和使用lambda大概有20倍的差距，并行流的差距为2倍，
        // 普通流和并行流的差距有10倍
        // 然后随着数据量的上升 普通迭代和并行相距不大 ，继续上升数据流，并行流效率将比普通迭代和普通流快
        // 因此 并行流适合处理大数据的情况下
    }

    /**
     * https://blog.csdn.net/qq_20009015/article/details/84892083
     */
    public static void streamDemo(int number) {
        List<Apple> appleList = initAppleList(number);

        long startFor = System.currentTimeMillis();

        // 常规处理
        Map<String, List<Apple>> AppMap = new HashMap<>();
        for (Apple apple : appleList) {
            if (apple.getWeight() > 150) {
                if (AppMap.get(apple.getColor()) == null) {
                    List<Apple> list = new ArrayList<>();
                    list.add(apple);
                    AppMap.put(apple.getColor(), list);
                } else {
                    AppMap.get(apple.getColor()).add(apple);
                }
            }
        }

        long endFor = System.currentTimeMillis();
        System.out.println("普通迭代分类结束：用时:" + (endFor - startFor));
        // =======================================================================

        // java8提供的流api实现
        long startStream = System.currentTimeMillis();
        Map<String, List<Apple>> AppMap2 = appleList.stream()
                .filter(a -> a.getWeight() > 150)
                .collect(groupingBy(Apple::getColor));
        long endStream = System.currentTimeMillis();
        System.out.println("普通流分类结束：用时:" + (endStream - startStream));


        // =======================================================================
        // java8提供的流api实现，内部迭代
        long startPara = System.currentTimeMillis();
        Map<String, List<Apple>> AppMap3 = appleList.parallelStream()
                .filter(a -> a.getWeight() > 150)
                .collect(groupingBy(Apple::getColor));
        long endPara = System.currentTimeMillis();
        System.out.println("并行流分类结束：用时:" + (endPara - startPara));


    }

    public static List<Apple> initAppleList(int number) {
        List<Apple> apples = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Apple apple = new Apple();
            int rad = (int) (Math.random() * 300);
            if (rad % 2 == 1) {
                apple.setColor("green");
            } else {
                apple.setColor("yellow");
            }
            apple.setWeight(rad);
            apples.add(apple);
        }
        return apples;
    }

    static class Apple {
        private String color;
        private Integer weight;

        public Apple() {
        }

        public Apple(String color, Integer weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }
    }

}
