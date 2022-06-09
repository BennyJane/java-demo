package org.example.com.match.Huawei;

import java.util.*;

/**
 * 题目描述
 * 在计算机中，CPU 只能和高速缓存 Cache 直接交换数据。当所需的内存单元不在 Cache 中时，则需要从主存里把数据调入 Cache。此时，如果 Cache 容量已满，则必须先从中删除一个。
 * 例如，当前 Cache 容量为 3，且已经有编号为 10 和 20 的主存单元。
 * 此时，CPU 访问编号为 10 的主存单元，Cache命中。
 * 接着，CPU 访问编号为 21 的主存单元，那么只需将该主存单元移入 Cache 中，造成一次缺失（Cache Miss）。
 * 接着，CPU 访问编号为 31 的主存单元，则必须从 Cache 中换出一块，才能将编号为 31 的主存单元移入 Cache，假设我们移出了编号为 10 的主存单元。
 * 接着，CPU 再次访问编号为 10 的主存单元，则又引起了一次缺失。我们看到，如果在上一次删除时，删除其他的单元，则可以避免本次访问的缺失。
 * 在现代计算机中，往往采用 LRU（最近最少使用）的算法来进行 Cache 调度——可是，从上一个例子就能看出，这并不是最优的算法。
 * 对于一个固定容量的空 Cache 和连续的若干主存访问请求，聪聪想知道如何在每次 Cache 缺失时换出正确的主存单元，以达到最少的 Cache 缺失次数。
 * <p>
 * 输入
 * 输入文件第一行包含两个整数 N 和 M，分别代表了主存访问的次数和 Cache 的容量。
 * 第二行包含了 N 个空格分开的正整数，按访问请求先后顺序给出了每个主存块的编号。
 * <p>
 * 输出
 * 输出一行，为 Cache 缺失次数的最小值。
 * <p>
 * 样例
 * 输入样例 1
 * 6 2
 * 1 2 3 1 2 3
 * 输出样例 1
 * 4
 *
 * 参考文章
 * https://www.nowcoder.com/questionTerminal/775370975e0246dbb535292db33aa264?answerType=1&f=discussion
 *
 * https://blog.csdn.net/qian2213762498/article/details/81810014
 */
public class Q3 {

    // TODO 反向思考：优先保留未来访问频率最大的值
    // 贪心思想：每次删去存在缓存中，且该数字在后续出现的索引下标最大（或不存在）
    public static final int limit = 100050;
    public static Map<Integer, Integer> map;
    public static boolean[] cache = new boolean[limit];
    public static int[] nxtIndex = new int[limit];
    public static int[] nums = new int[limit];

    public static int num = 0;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }

            // 记录每个值右侧出现第一个相同值的索引
            // 存储缓存值
            for (int i = n - 1; i >= 0; i--) {
                int c = nums[i];
                if (map.containsKey(c)) {
                    nxtIndex[i] = map.get(c);
                } else {
                    nxtIndex[i] = limit - 1;
                }
                map.put(c, i);
            }

            PriorityQueue<Integer> queue = new PriorityQueue<>(m,
                    (a, b) -> b - a);

            int miss = 0;
            for (int i = 0; i < n; i++) {
                if (!cache[i]) {
                    miss++;
                    if (num < m) {
                        num++;
                        cache[nxtIndex[i]] = true;
                        queue.offer(nxtIndex[i]);
                    } else {
                        int rightIndex = queue.poll();
                        cache[rightIndex] = false;
                        cache[nxtIndex[i]] = true;
                        queue.offer(nxtIndex[i]);
                    }
                } else {
                    cache[nxtIndex[i]] = true;
                    queue.offer(nxtIndex[i]);
                }


            }

            System.out.println(miss);
        }
    }

}

