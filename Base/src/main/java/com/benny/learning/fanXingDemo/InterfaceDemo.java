package com.benny.learning.fanXingDemo;

// https://blog.csdn.net/s10461/article/details/53941091
public interface InterfaceDemo<E> {
    public E next();
}


class InterImpl implements InterfaceDemo<String> {
    @Override
    public String next() {
        System.out.println("next...1");
        return "next";
    }
}

class InterImpl2<T> implements InterfaceDemo<T> {
    @Override
    public T next() {
        System.out.println("next...2");
        return null;
    }


}

class InterfaceTest {
    public static void main(String[] args) {
        InterImpl cl = new InterImpl();
        cl.next();

        InterImpl2<String> cl2 = new InterImpl2<String>();
        cl2.next();

        InterImpl2<Integer> cl3 = new InterImpl2<Integer>();
        cl3.next();
    }
}
