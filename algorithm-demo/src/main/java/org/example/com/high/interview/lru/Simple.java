package org.example.com.high.interview.lru;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 基于LinkedHashMap实现LRU
 */
public class Simple extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public Simple(int capacity) {
        // accessOrder: 设置为按照顺序排列
        super(capacity, 0.75F, true);
    }
    // TODO 调用父类方法
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
