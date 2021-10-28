package benny.jane.com.leetcode.other;

import java.util.LinkedList;

/**
 * 1381. 设计一个支持增量操作的栈
 * https://leetcode-cn.com/problems/design-a-stack-with-increment-operation/
 */
public class Q10 {

    class CustomStack {
        LinkedList<Integer> st = new LinkedList();
        int limit = 0;

        public CustomStack(int maxSize) {
            this.limit = maxSize;
        }

        public void push(int x) {
            if (this.st.size() < this.limit) {
                st.addLast(x);
            }
        }

        public int pop() {
            if (st.isEmpty()) {
                return -1;
            } else {
                return st.removeLast();
            }
        }

        public void increment(int k, int val) {
            if (k > this.st.size()) {
                k = this.st.size();
            }
            for (int i=0; i<=k; i++) {
                int newVal = st.get(i) + val;
                st.set(i, newVal);
            }
        }
    }
}
