package com.benny.jane.filelock;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.Date;

public class FileLockDemo02 {

    static int count = 0;

    static String root = "G:\\BennyOfJava\\java-demo\\java-nio-demo\\src\\main\\java\\com.benny.jane\\filelock";

    public static void fileLock1() {
        String lockFile = String.format("%s/%s", root, "lock1.txt");
        File file = new File(lockFile);
        FileLock lock = null;
        FileChannel channel = null;
        try {
            channel = new RandomAccessFile(file, "rw").getChannel();
            lock = channel.lock();
            System.out.println("get lock:" + new Date().getTime());
            Thread.sleep(5000);
            // 核心逻辑
            count++;
            ByteBuffer sendBuffer = ByteBuffer.wrap((new Date() + "FileLockDemo02-写入\n").getBytes());
            channel.write(sendBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock != null) {
                try {
                    lock.release();
                    lock = null;
                    System.out.println("release lock:" + new Date().getTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (channel != null) {
                try {
                    channel.close();
                    channel = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void main(String[] args) {
        int size = 1;
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        fileLock1();
                        System.out.println("02 done");

                    } catch (OverlappingFileLockException e) {
                        System.out.println(e.getMessage());
                        continue;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }

            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("res: " + count);
    }
}
