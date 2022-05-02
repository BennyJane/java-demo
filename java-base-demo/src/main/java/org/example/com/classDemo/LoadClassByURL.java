package org.example.com.classDemo;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class LoadClassByURL extends URLClassLoader {

    public LoadClassByURL(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public LoadClassByURL(URL[] urls) {
        super(urls);
    }

    public LoadClassByURL(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}
