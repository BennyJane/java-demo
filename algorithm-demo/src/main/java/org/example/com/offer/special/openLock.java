package org.example.com.offer.special;

import org.omg.CORBA.TRANSACTION_MODE;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

//剑指 Offer II 109. 开密码锁
public class openLock {
    Set<Integer> deadSet = new HashSet<>();
    int step = -1;
    int TARGET;

    public int openLock2(String[] deadends, String target) {
        for (String s : deadends) {
            deadSet.add(Integer.parseInt(s));
        }
        TARGET = Integer.parseInt(target);
        int[] ori = new int[]{0, 0, 0, 0};
        dfs(ori, 0);
        return step;
    }

    private void dfs(int[] mat, int cnt) {
        int cur = cal(mat);
        if (!deadSet.isEmpty() && deadSet.contains(cur)) {
            return;
        }
        if (cur == TARGET) {
            step = Math.min(step, cnt);
            return;
        }
        mat[0] += 1;
        dfs(mat, cnt + 1);
        mat[0] -= 2;

        mat[1] += 1;
        dfs(mat, cnt + 1);
        mat[1] -= 2;

        mat[2] += 1;
        dfs(mat, cnt + 1);
        mat[2] -= 2;

        mat[3] += 1;
        dfs(mat, cnt + 1);
        mat[3] -= 2;
    }

    private int cal(int[] mat) {
        int res = 0;
        for (int i = 0; i < 4; i++) {
            res += mat[3 - i] * 10 ^ i;
        }
        return res;
    }


    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }

        Set<String> dead = new HashSet<>();
        for (String d : deadends) {
            dead.add(d);
        }

        if (dead.contains("0000")) {
            return -1;
        }

        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        Set<String> seen = new HashSet<>();
        seen.add("0000");

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String status = queue.poll();
                for (String nxtString : getNxtString(status)) {
                    if (!seen.contains(nxtString) && !dead.contains(nxtString)) {
                        if (nxtString.equals(target)) {
                            return step;
                        }
                        queue.offer(nxtString);
                        seen.add(nxtString);
                    }
                }
            }
        }

        return -1;
    }

    private char upper(char x) {
        return x == '9' ? '0' : (char) (x +1);
    }

    private char down(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    private List<String> getNxtString(String status) {
        List<String> ret = new ArrayList<>();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; i++) {
            char num = array[i];
            // 加1
            array[i] = upper(num);
            ret.add(new String(array));
            // 减1
            array[i] = down(num);
            ret.add(new String(array));
            // 复原
            array[i] = num;
        }
        return ret;
    }
}
