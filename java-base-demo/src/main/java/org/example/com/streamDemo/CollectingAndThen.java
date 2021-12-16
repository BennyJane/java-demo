package org.example.com.streamDemo;

import org.example.com.CommonData;

import java.util.stream.Collectors;

public class CollectingAndThen {
    public static void main(String[] args) {
        methodTest();
    }

    static void methodTest() {
        String res = CommonData.servers.stream().collect(Collectors.collectingAndThen(
                Collectors.joining(","), String::toUpperCase
        ));
        System.out.println(res);

    }
}
