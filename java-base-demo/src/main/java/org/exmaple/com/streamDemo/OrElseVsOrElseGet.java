package org.exmaple.com.streamDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrElseVsOrElseGet {
    static List<User> users = new ArrayList() {
        {
            add(new User("1", "benny1"));
            add(new User("2", null));
            add(new User("3", "benny1"));
        }
    };

    public static void main(String[] args) {
        methodTest();
        methodTest2();

    }

    static void methodTest() {
        List<String> names;
        names = users.stream().map(
                user -> (user.getName() != null)
                        ? user.getName()
                        : "暂无"
        ).collect(Collectors.toList());
        System.out.println("三元表达式： " + names);

        names = users.stream().map(
                user -> Optional.ofNullable(user.getName()).orElse("暂无")
        ).collect(Collectors.toList());
        System.out.println("orElse： " + names);
    }

    static void methodTest2() {
        List<String> words;
        words = Arrays.asList("hi", "hello", "bye", "goodbye");
        // findFirst() 返回Optional
        String s1 = words.stream().filter(word -> word.contains("o")).findFirst().orElse("没有符合条件的数据");
        System.out.println("orElse： " + s1);

        // filter 选择后，没有符合条件的对象
        words = Arrays.asList("hi", "bye");
        String s2 = words.stream().filter(word -> word.contains("o")).findFirst().orElse("没有符合条件的数据");
        System.out.println("orElse： " + s2);
    }
}

class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}