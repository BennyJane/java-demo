package benny.jane.com.java8Demo;

import java.util.function.Supplier;

public interface interfaceFactory {
    // 工厂函数，使用静态方法 类实例
    static FunctionInterface create(Supplier<FunctionInterface> supplier) {
        return supplier.get();
    }
}
