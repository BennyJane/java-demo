package org.example.com;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class BaseDemo {
    ArrayList<Integer> list = new ArrayList<>();
    ReentrantLock rtl = new ReentrantLock();

    static {
        System.out.println("this is a static method!!");
    }

    public static void main(String[] args) {
        BaseDemo baseDemo = new BaseDemo();
        baseDemo.funcExc();
    }

    private void funcExc() {
        System.out.println(rtl);
    }
}
