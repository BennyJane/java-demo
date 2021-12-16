package org.example.com.tryCatchDemo;

import java.io.*;
import java.util.zip.ZipFile;

public class ComDemo {
    public static void main(String[] args) {

    }

    /**
     * 常用关闭资源的方式，存在的问题： 如果try块和finally块中的方法都抛出异常那么try块中的异常会被抑制（suppress），只会抛出finally中的异常，而把try块的异常完全忽略。
     *
     * @throws IOException
     */
    static void method() throws IOException {
        String path = "//mnt//dist";
        BufferedReader br = new BufferedReader(new FileReader(path));

        try {
            br.readLine();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // finally 块中抛出的异常，并没有被捕获，且会覆盖(抑制)try catch中抛出的异常
            if (br != null) br.close();
        }
    }

    /**
     * 推荐的关闭资源的方式
     * <p>
     * 函数执行结束之后抛出的是try块的异常，而try-with-resources语句关闭过程中的异常会被抑制，放在try块抛出的异常的一个数组里。（上面的非try-with-resources例子抛出的是finally的异常，而且try块的异常也不会放在fianlly抛出的异常的抑制数组里）
     * 可以通过异常的public final synchronized Throwable[] getSuppressed() 方法获得一个被抑制异常的数组。
     * try块抛出的异常调用getSuppressed()方法获得一个被它抑制的异常的数组，其中就有关闭资源的过程产生的异常。
     */
    static void methodBetter(String path, String zipFileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine();
        }
    }
}
