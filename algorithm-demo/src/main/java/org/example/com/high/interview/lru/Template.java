package org.example.com.high.interview.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * 在双向链表中存储原始数据的key:value
 * 使用Map存储key:node的映射关系，key指向双向链表中对应的节点
 *
 * put,get操作节点都需要移动到链表首部
 *
 * put：
 *  - 新增节点，需要同时在链表、map中同时新增；
 *  - 更新节点：更新node的值，并启动到首部
 *  - 新增节点，超出capacity时，需要同时删除链表末尾元素，map中key:node
 */
public class Template {

    private class DLNode {
        // 每个节点存储原始数据：key:value
        public int key;
        public int value;

        // 保存前后节点连接信息
        public DLNode pre;
        public DLNode nxt;

        // 无参构造函数：用于初始化辅助节点head tail
        public DLNode() {
        }

        public DLNode(int key, int val) {
            this.key = key;
            this.value = val;
        }
    }

    // 保存key:node 映射关系
    Map<Integer, DLNode> map;
    // 辅助节点： head tail
    private DLNode head;
    private DLNode tail;
    // 保存容量
    private int capacity;
    // 保存长度: FIXME 链表没有直接获取长度的函数
    private int length;

    public Template(int capacity) {
        // 参数初始化
        this.capacity = capacity;
        length =0;
        // 每个实例都需要重新生成，清空原来的数据
        map = new HashMap<>();
        // 初始化辅助节点，设置连接关系
        head = new DLNode();
        tail = new DLNode();
        // FIXME 连接关系
        head.nxt = tail;
        tail.pre = head;
    }

    public int get(int key) {
        // 先通过map，判断是否存在
        // 不存在，直接返回-1
        if (!map.containsKey(key)) return -1;

        // 存在，通过map获取node中保存的值
        DLNode node = map.get(key);
        // TODO 并将该节点移动到链表首部，表示最新访问状态
        // 先删除，再添加到head后面
        moveHead(node);
        return node.value;
    }

    public void put(int key, int val) {
        // FIXME 先通过map判断key是否存在
        if (map.containsKey(key)) {
            // 存在,直接修改node的值，并将其移动到首部
            DLNode node = map.get(key);
            node.value = val;
            // 移动到首部
            moveHead(node);
        } else {
            // TODO 不存在，需要新增，并考虑超出容量的情况
            DLNode newNode = new DLNode(key, val);
            // 链表+map：同时新增
            addHead(newNode);
            map.put(key, newNode);
            // length达到capacity后，不再增加，只需要删除节点
            if (length == capacity) {
                // FIXME 删除末尾节点（最少被访问的节点）：链表、map 都需要删除
                DLNode second = deleteTail();
                int delKey = second.key;
                map.remove(delKey);
            } else {
                length++;
            }
        }
    }

    // 从双向链表中删除该节点
    private void deleteNode(DLNode node) {
        // 核心：只需要出来前一个节点的nxt， 后一个节点的pre
        // 当前节点的pre，nxt 不需要处理
        node.pre.nxt = node.nxt;
        node.nxt.pre = node.pre;
    }

    private void addHead(DLNode node) {
        // 添加node的前后关系
        node.pre = head;
        node.nxt = head.nxt;

        // 先处理head.nxt，再处理head
        head.nxt.pre = node;
        head.nxt = node;
    }

    private void moveHead(DLNode node) {
        deleteNode(node);
        addHead(node);
    }

    // 删除末尾节点
    private DLNode deleteTail() {
        DLNode node = tail.pre;
        deleteNode(node);
        return node;
    }

}
