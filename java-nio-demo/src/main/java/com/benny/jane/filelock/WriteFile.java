package com.benny.jane.filelock;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/**
 * FileLock是进程锁，
 * 不同进程内调用 channel.lock() 会阻塞
 * 同一个进程内多线程访问会抛出OverlappingFileLockException异常
 */
public class WriteFile implements Runnable {
    @Override
    public void run() {
        // TODO Auto-generated method stub
        String lockFile = "G:\\BennyOfJava\\java-demo\\java-nio-demo\\src\\main\\java\\com.benny.jane\\filelock\\lock3.txt";
        File file = new File(lockFile);
        FileLock lock = null;
        FileChannel channel = null;
        RandomAccessFile accessFile = null;
        try {
            accessFile = new RandomAccessFile(file, "rw");
            channel = accessFile.getChannel();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("accessFile");
        }
        // 循环获取锁
        while (true) {
            try {
                System.out.println("try get lock  thread name is :" + Thread.currentThread().getName());
                lock = channel.lock();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("other thread is handling file  please wait");
                e.printStackTrace();
            } catch (OverlappingFileLockException e) {
                System.out.println("OverlappingFileLockException happen ...");
                e.printStackTrace();
            }
            if (lock != null) {
                break;
            }
            System.out.println("other thread is handling file  please wait");
        }
        // 将指针移动到文件末尾
        try {
            accessFile.seek(accessFile.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("lock....");
        for (int i = 1; i <= 10; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append("this is line:" + i + "，yes thread name:" + Thread.currentThread().getName() + "\n");
            try {
                accessFile.write(sb.toString().getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            lock.release();
            channel.close();
            accessFile.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        String lockFile = "G:\\BennyOfJava\\java-demo\\java-nio-demo\\src\\main\\java\\com.benny.jane\\filelock\\lock3.txt";

        File file = new File(lockFile);
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }
        } catch (Exception e) {
            System.out.println("file create fail");
        }
        System.out.println("---------------------init  file is ok");
        WriteFile w1 = new WriteFile();
        WriteFile w2 = new WriteFile();
        Thread thread1 = new Thread(w1);
        Thread thread2 = new Thread(w2);
        thread1.start();
        thread2.start();
    }
}