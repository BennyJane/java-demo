package org.exmaple.com.streamDemo;

import com.benny.learning.CommonData;

import java.util.ArrayList;
import java.util.List;
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
