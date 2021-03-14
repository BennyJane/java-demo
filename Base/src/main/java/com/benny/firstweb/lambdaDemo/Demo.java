package com.benny.firstweb.lambdaDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * lambda 匿名函数
 * person -> person.getName()
 * person::getName
 * <p>
 * () -> new HashMap()
 * HashMap::new
 *
 * https://blog.csdn.net/lsmsrc/article/details/41747159
 */
public class Demo {
    public static void main(String[] args) {
        // 把List<String>里面的String全部大写并返还新的ArrayList<String>
        List<String> collected = new ArrayList<>();
        collected.add("benny");
        collected.add("jane");
        List<String> res1 = collected.stream()
                .map(string -> string.toUpperCase(Locale.ROOT))
                .collect(Collectors.toList());
        System.out.println(res1);

        // lambda
        List<String> res2 = collected.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(res2);

    }
}
