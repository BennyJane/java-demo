package org.example.com.arrayDemo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://blog.csdn.net/wang_8101/article/details/83067860
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        method1();
        method2();
    }

    private static void method1() {
        Map<String, Integer> seqMap = new LinkedHashMap<>();
        seqMap.put("c", 100);
        seqMap.put("d", 200);
        seqMap.put("a", 500);
        seqMap.put("d", 300);
        for (Map.Entry<String, Integer> entry : seqMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

    private static void method2() {
        LRUCache<String,Object> cache = new LRUCache<>(3);
        cache.put("a","abstract");
        cache.put("b","basic");
        cache.put("c","call");
        cache.get("a");
        cache.put("d","滴滴滴");
        // 从左往右：超出容量后删除的先后顺序，左侧先删除，右侧为最新访问数据
        System.out.println(cache); // 输出为：{c=call, a=abstract, d=滴滴滴}
        for (Map.Entry<String, Object> entry : cache.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}


class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int maxEntries;

    public LRUCache(int maxEntries) {
        super(16, 0.75f, true);
        this.maxEntries = maxEntries;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxEntries;
    }

}

