package org.example.com.classDemo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;

public class CustClassLoader extends ClassLoader {
    public CustClassLoader() {
    }

    /**
     * 构造器
     * @param parent 父类加载器
     */
    public CustClassLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * 核心重写方法
     * @param name 加载类的名称
     * @return 加载后的类
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 加载类文件
        File classFile = getClassFile(name);
        try {
            // 类文件读取为二进制流文件
            byte[] bytes = getClassBytes(classFile);
            // TODO 核心方法 .defineClass()
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        } catch (Exception e) {
            System.out.println(e);
        }

        return super.findClass(name);
    }

    private File getClassFile(String name) {
        String classPath = "SupportData/TargetLoadedClass.txt";
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
