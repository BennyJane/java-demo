package org.exmaple.com.streamDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapDemo {
    public static void main(String[] args) {
        methodTest();
    }

    static void methodTest() {
        List<String> strs = Arrays.asList("Hello", "world", "benny jane");
        List<String> res = strs.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(res);
    }
}
