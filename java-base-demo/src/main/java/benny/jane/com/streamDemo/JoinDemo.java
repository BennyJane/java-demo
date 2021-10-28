package benny.jane.com.streamDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JoinDemo {
    public static void main(String[] args) {

    }

    static void methodTest() {
        List<String> servers = new ArrayList<>();
        servers.add("Felordcn");
        servers.add("Tomcat");
        servers.add("Jetty");
        servers.add("Undertow");
        servers.add("Resin");

        System.out.println(servers.stream().collect(Collectors.joining()));
        System.out.println(servers.stream().collect(Collectors.joining(",")));
        System.out.println(servers.stream().collect(Collectors.joining(",", "[", "]")));

    }
}
