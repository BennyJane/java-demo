package org.example.com.high.interview.design;

import java.util.*;

/**
 * 381.O（1）时间插入、删除、获取随机元素-允许重复
 */
public class RandomizedCollection {

    private List<Integer> list;
    private Map<Integer, Set<Integer>> cache;
    private Random random;

    public RandomizedCollection() {
        cache = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        list.add(val);
        if (cache.containsKey(val)) {
            Set<Integer> tmp = cache.get(val);
            tmp.add(list.size() - 1);
            cache.put(val, tmp);
            return false;
        }
        Set<Integer> initList = new HashSet<>();
        initList.add(list.size() - 1);
        cache.put(val, initList);
        return true;
    }

    public boolean remove(int val) {
        if (!cache.containsKey(val)) {
            return false;
        }

        // FIXME 如何从set中随机取一个值 ==》 遍历操作
        Set<Integer> tmp = cache.get(val);
        Iterator<Integer> it = tmp.iterator();
        int delIndex = it.next();

        // 映射数组删除末尾元素
        cache.get(val).remove(delIndex);

        int tailIndex = list.size() -1;
        int tail = list.get(tailIndex);
        list.set(delIndex, tail);
        list.remove(tailIndex);
        // TODO 更新tail在cache中的记录, 只有当delIndex != tailIndex时，才需要执行
        if (delIndex < tailIndex) {
            // 确保删除的索引，不是末尾索引，才需要重新添加索引记录
            cache.get(tail).remove(tailIndex);
            cache.get(tail).add(delIndex);
        }
        // 删除长度为0的Key
        if (cache.get(val).size() == 0) {
            cache.remove(val);
        }
        return true;
    }

    public int getRandom() {
        int index = random.nextInt(list.size());
        //int selectIndex = (int) Math.random() * list.size();
        return list.get(index);
    }
}
