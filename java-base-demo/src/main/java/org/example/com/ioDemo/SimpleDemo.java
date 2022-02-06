package org.example.com.ioDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleDemo {

    public static void readInput() throws IOException {
        // System.in 接受控制台输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符, 按下回车键确认, 按下 q 键退出.");
        char c;

        do {
            // read() 方法读取单个字符; 当流结束时,返回-1, 该方法抛出IOException
            c = (char) br.read();
            System.out.println(c);
        } while (c != 'q');
    }

    public static void readLineInput()  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符, 按下回车键确认, 输入end推出.");
        String str;
        try {
            do {
                str = br.readLine();
                System.out.println(str);
            } while (!str.equals("end"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        try {
//            SimpleDemo.readInput();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        readLineInput();
    }
}
