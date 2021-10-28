package benny.jane.com.leetcode.linkedList.middle;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LRUCache {

    int index = 0;
    int limit = 0;
    PriorityQueue<int[]> queue;
    Map<Integer, Integer> map = new HashMap<>();

    public LRUCache(int capacity) {
        limit = capacity;
        this.queue = new PriorityQueue<>(capacity, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
    }

    public int get(int key) {
        // TODO 更新访问时间
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if (!queue.isEmpty() && queue.size() == limit) {
            int[] top = queue.poll();
            map.remove(top[0]);
        }
        map.put(key, value);
        queue.add(new int[]{key, index});
        index++;
    }

    public static void main(String[] args) {
        LRUCache q = new LRUCache(2);
        q.put(1,1);
        q.put(2,2);
        q.get(1);
        q.put(3,3);
        q.get(2);
        q.put(4,4);
        q.get(1);
        q.get(3);
        q.get(4);
    }
}
