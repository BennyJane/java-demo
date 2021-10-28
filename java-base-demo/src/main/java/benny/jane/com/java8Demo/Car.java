package benny.jane.com.java8Demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 方法引用案例
 */
public class Car {
    // 类静态方法
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    /**
     * 静态方法，接收参数
     *
     * @param car
     */
    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    // 实例方法（成员方法）：接收参数
    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    // 实例方法（成员方法）：无参数
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    /**
     * 方法引用的四种方法
     * @param args
     */
    public static void main(String[] args){
        // 构造器引用： Class::new Class<T>::new; 该构造方法没有参数
        final Car car = Car.create(Car::new);
        final Car car1 = Car.create(Car::new);
        System.out.println(car.toString() + "\n" + car1.toString());
        final List<Car> cars = Arrays.asList(car,car1);

        // 静态方法引用： Class::static_method; 一般接收Car类型的餐胡
        cars.forEach(Car::collide);

        // 某个类的成员方法的引用： Class::method ,该方法没有定义入参
        // 实际调用的是实例对象上的方法
        cars.forEach(Car::repair);
        // 报错
        //cars.forEach(car::repair);

        // 某个实例对象的成员方法： instance::method
        // follow接收一个Car类型的参数
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);
    }

}
