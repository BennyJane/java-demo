package com.example.demo.Learn;

public class Demo1 {
    private String name;
    public String site;
    public String[] info = {"1", "1", "1", "1"};

    public Demo1(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println("run ...");
    }
}
