package benny.jane.com.leetcode.array.simple;

import com.benny.learning.algorithm.Q;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 556. 下一个更大元素 III
 * https://leetcode-cn.com/problems/next-greater-element-iii/
 */
public class Q36 {
    public int nextGreaterElement(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        int len = arr.length;

        List<Character> list = new ArrayList<>();

        // 逆序遍历
        for (int i = len - 1; i >= 0; i--) {
            char cur = arr[i];
            // 找出右侧大于cur的最小值
            Character targetMax = findMax(arr, cur, i);
            // 找到第一个位置，满足条件：右侧存在大于该位置数值
            if (targetMax != 'A') {
                arr[i] = targetMax;
                // 需要删除list内一个targetMax
                list.add(cur);
                list.remove(targetMax);

                StringBuffer sb = new StringBuffer();
                for (int j = 0; j <= i; j++) {
                    sb.append(arr[j]);
                }

                // 需要对list内数值进行排序
                List<Character> newList = list.stream().sorted().collect(Collectors.toList());
                for (char c : newList) {
                    sb.append(c);
                }
                // TODO 需要捕捉异常
                try {
                    return Integer.parseInt(sb.toString());
                } catch (Exception e) {
                    return -1;
                }
            }
            list.add(cur);
        }
        return -1;
    }

    private char findMax(char[] arr, char c, int start) {
        Character target = null;
        for (int i = start + 1; i < arr.length; i++) {
            if (arr[i] > c) {
                if (target == null) {
                    target = arr[i];
                } else {
                    target = target - arr[i] >0 ? arr[i] : target;
                }
            }
        }
        return target == null ? 'A' : target;
    }

    public static void main(String[] args) {
        Q36 q = new Q36();
//        q.nextGreaterElement(2147483486);
        q.nextGreaterElement(12443322);
    }
}


