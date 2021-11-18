package org.exmaple.com.optionalDemo;

import java.util.Arrays;
import java.util.List;

public class OrElseVsOrElseGet {
    public static void main(String[] args) {
        methodTest1();
    }

    static void methodTest1() {
        List<Integer> list = Arrays.asList();   // 这里不能直接赋值为null
        System.out.println(list);   // 空数组，不是null -》 使用流，获取的是null
        Integer res;
        // reduce 返回Optional对象；同样的有： findFirst
        res = list.stream().reduce(Integer::sum).orElse(outPut("orElse"));
        res = list.stream().reduce(Integer::sum).orElseGet(() -> outPut("orElseGet"));
    }

    static Integer outPut(String name) {
        System.out.println(name + " running...");
        return 1;
    }
}
