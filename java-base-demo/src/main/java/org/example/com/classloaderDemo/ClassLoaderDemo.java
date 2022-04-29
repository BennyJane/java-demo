package org.example.com.classloaderDemo;

import sun.misc.Launcher;

public class ClassLoaderDemo {

    public static void LauncherTest() {
        ClassLoader classloader = Launcher.getLauncher().getClassLoader();
        System.out.println(classloader);
        System.out.println(classloader.getParent());

        Launcher launcher = new Launcher();
    }

    public static void main(String[] args) {
        LauncherTest();
    }
}
