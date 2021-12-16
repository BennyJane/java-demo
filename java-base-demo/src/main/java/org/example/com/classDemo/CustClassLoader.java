package org.example.com.classDemo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class CustClassLoader extends ClassLoader {
    public CustClassLoader() {
    }

    public CustClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File classFile = getClassFile(name);
        try {
            byte[] bytes = getClassBytes(classFile);
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        } catch (Exception e) {
            System.out.println(e);
        }

        return super.findClass(name);
    }

    private File getClassFile(String name) {
        String classPath = "F:\\BennyOfJava\\java-demo\\java-base-demo\\data\\TargetLoadedClass.class";
        File file = new File(classPath);
        return file;
    }

    /**
     * 加载.class文件, 使用字节流
     *
     * @param file
     * @return
     * @throws Exception
     */
    private byte[] getClassBytes(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(byteArrayOutputStream);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true) {
            int i = fc.read(by);
            if (i == 0 || i == -1) {
                break;
            }
            by.flip();
            wbc.write(by);
            by.clear();
        }

        fis.close();
        System.out.println(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        CustClassLoader custClassLoader = new CustClassLoader();
        Class<?> cls = Class.forName("org.example.com.classDemo.TargetLoadedClass", true, custClassLoader);
        Object obj = cls.newInstance();
        System.out.println(obj);
        // 如何同目录下存在TargetLoadedClass类文件，则下面输出 org.example.com.classDemo.$AppClassLoader@5d888759
        System.out.println(obj.getClass().getClassLoader());
    }
}
