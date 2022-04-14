package org.example.com.high.interview.design;

import java.util.*;

/**
 * 380.O(1)时间插入、删除、获取随机元素
 * https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 */
public class RandomizedSet {

    // 存储Value
    private List<Integer> list;
    // 存储Value:index, index:Value在数组中的索引
    private Map<Integer, Integer> cache;
    private int size;

    private static Random random = new Random();

    public RandomizedSet() {
        cache = new HashMap<>();
        list = new ArrayList<>();
        size = 0;
    }

    public boolean insert(int val) {
        if (cache.containsKey(val)) {
            return false;
        }

        // 新增元素，cache记录val:index的映射关系
        list.add(val);
        cache.put(val, size);
        size++;
        return true;
    }

    // 删除操作: cache直接调用remove(val)，
    // list先用末尾元素覆盖数组中val元素，再删除末尾元素，并更新cache中末尾元素对应索引
    public boolean remove(int val) {
        if (!cache.containsKey(val)) {
            return false;
        }
        // TODO 先用尾部元素覆盖待删除数据，然后更新cache、list、size
        int index = cache.get(val);
        int tailIndex = size - 1;
        int tail = list.get(tailIndex);
        cache.put(tail, index);
        list.set(index, tail);
        // 长度减1
        size--;
        // 删除
        cache.remove(val);
        list.remove(tailIndex);
        return true;
    }

    public int getRandom() {
        int index = random.nextInt(size);
        return list.get(index);
    }
}
