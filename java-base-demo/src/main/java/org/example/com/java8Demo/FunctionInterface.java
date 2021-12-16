package org.example.com.java8Demo;

import java.util.Objects;

/**
 * 函数接口：只有一个函数的接口，该接口可以隐式的转化为Lambda表达式
 *
 * 从上面四个接口的源码中可以看到，接口中不一定只有一个方法的才是函数式接口，
 * 一个接口是函数接口的充要条件是有且仅有一个抽象方法。
 * 接口中的其他方法可以通过加上default关键字提供默认实现，或者是一个static修饰的静态方法。
 */
@FunctionalInterface
public interface FunctionInterface {
    void uniqueFunc();
    // 定义第二个方法，编译阶段就会报错
    //void secondFunc();

    // 可以定义默认方法; 实现类继承，但不需要实现
    default void notRequired() {
        System.out.println("FunctionInterface default method");
    }
    // 静态方法
    static void staticMethod() {
        System.out.println("static method");
    }
}

//Consumer:消费型接口(无返回值，有一个泛型的输入参数）
@FunctionalInterface
interface Consumer<T> {
    void accept(T t);
    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}

//Supplier:供给型接口(无输入参数，有一个泛型的返回值)
@FunctionalInterface
interface Supplier<T> {
    T get();
}


//Function：函数型接口（有一个泛型的输入参数和一个泛型的返回值）
@FunctionalInterface
interface Function<T, R> {
    R apply(T t);
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }
    static <T> Function<T, T> identity() {
        return t -> t;
    }
}


//Predicate:断言型(判断型)接口：(有一个泛型的输入参数，返回一个bool值)
@FunctionalInterface
interface Predicate<T> {
    boolean test(T t);
    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }
    default Predicate<T> negate() {
        return (t) -> !test(t);
    }
    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }
    static <T> Predicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : object -> targetRef.equals(object);
    }
}
