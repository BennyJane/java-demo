package org.example.com.lambdaDemo;

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
 *
 * 参考文章：
 * https://blog.csdn.net/lsmsrc/article/details/41747159
 * http://zh.lucida.me/blog/java-8-lambdas-insideout-language-features/
 *
 */
public class Demo {
    /**
     * 常用的形式
     * (arg1, arg2...) -> { body }
     * (type1 arg1, type2 arg2...) -> { body }
     *
     * 案例
     * (int a, int b) -> {  return a + b; }
     *
     * () -> System.out.println("Hello World");
     *
     * (String s) -> { System.out.println(s); }
     *
     * () -> 42
     *
     * () -> { return 3.1415 };
     */


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
