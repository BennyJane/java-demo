package org.exmaple.com.patterns.CopyOnWrite;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class Demo {

    ConcurrentHashMap<String, CopyOnWriteArraySet<String>> hashMap = new ConcurrentHashMap<>();

    public void add(String key, String val) {
        // hashMap.computeIfAbsent() 原子操作，并发时会阻塞
        CopyOnWriteArraySet<String> set = hashMap.computeIfAbsent(key, r -> new CopyOnWriteArraySet<>());
        set.add(val);
        // 写回hashMap中
        hashMap.put(key, set);
    }

    public void remove(String key) {
        CopyOnWriteArraySet<String> set = hashMap.get(key);
        if (set != null) {
            System.out.println(set);
            set.remove(key);
        }
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        d.add("name", "benny");
        d.add("name", "jane");
        System.out.println(d.hashMap);
        d.remove("name");
        System.out.println(d.hashMap);
        Long lNum = new Long(10);
    }


}
