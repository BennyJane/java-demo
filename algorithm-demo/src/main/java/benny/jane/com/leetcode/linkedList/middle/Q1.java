package benny.jane.com.leetcode.linkedList.middle;

import com.benny.learning.algorithm.utils.ListNode;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.org.apache.xerces.internal.impl.xs.identity.UniqueOrKey;

/**
 * 82. 删除排序链表中的重复元素 II
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class Q1 {
    // 思路：挑选出唯一节点，添加到新创建的链表尾部
    // 思路：使用栈结构、数组等存储容器，
    public ListNode deleteDuplicates3(ListNode head) {
        ListNode newHead = new ListNode();
        ListNode tail = newHead;
        ListNode pre = head;
        tail.next = pre;
        while (pre != null && pre.next != null) {
            if (pre.val != pre.next.val) {
                tail.next = pre;
                tail = tail.next;
                pre = pre.next;
            } else {
                int val = pre.val;
                while (pre != null && pre.val == val) {
                    pre = pre.next;
                }
            }
        }

        if (pre != null) {
            tail.next = pre;
            tail = tail.next;   // 此处必须位移至末尾，否则会被后面的判断覆盖最后一个节点
        }
        // TODO 不可省略： tail.next 设置为pre，并不能保证pre后续节点为null，可能有多余节点
        if (tail.next != null) {
            tail.next = null;
        }
        return newHead.next;
    }

    // 使用辅助头部节点
    public ListNode deleteDuplicates41(ListNode head) {
        ListNode ori = new ListNode();
        ListNode tail = ori;
        tail.next = head;
        while (tail.next != null && tail.next.next != null) {
            if (tail.next.val == tail.next.next.val) {
                int target = tail.next.val;
                while (tail.next != null && tail.next.val == target) {
                    // FIXME 此处应该更新到下一个节点： 而不是 tail = tail.next;
                    tail.next = tail.next.next;
                }
            } else {
                tail = tail.next;
            }
        }
        return ori.next;
    }


    // 递归
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        } else {
            int target = head.val;
            while (cur != null) {
                if (cur.val == target) {
                    cur = cur.next;
                } else {
                    break;
                }
            }
            return deleteDuplicates(cur);
        }
    }


}
