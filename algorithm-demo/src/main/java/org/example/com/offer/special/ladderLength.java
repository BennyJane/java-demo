package org.example.com.offer.special;


import java.util.*;

//127. 单词接龙
public class ladderLength {
    // 为每个单词添加序列号码
    Map<String, Integer> wordId = new HashMap<String, Integer>();
    // 建立图
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //创建图
        for (String word : wordList) {
            addEdge(word);
        }
        // 添加起始点
        addEdge(beginWord);
        // 图中不包含endWord 直接返回
        if (!wordId.containsKey(endWord)) {
            return 0;
        }

        // 广度优先搜索：双端搜索

        int[] disBegin = new int[nodeNum];
        Arrays.fill(disBegin, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord);
        disBegin[beginId] = 0;  // 此处，没有统计起点对整个长度的贡献
        // 从beginWord开始搜索
        Queue<Integer> queBegin = new LinkedList<Integer>();
        queBegin.offer(beginId);


        int[] disEnd = new int[nodeNum];
        Arrays.fill(disEnd, Integer.MAX_VALUE);
        int endId = wordId.get(endWord);
        disEnd[endId] = 0;
        // 从endWord开始搜索
        Queue<Integer> queEnd = new LinkedList<Integer>();
        queEnd.offer(endId);

        while (!queBegin.isEmpty() && !queEnd.isEmpty()) {
            // 每次处理同距离的一批数据
            int queBeginSize = queBegin.size();
            for (int i = 0; i < queBeginSize; ++i) {
                int nodeBegin = queBegin.poll();
                // disEnd: 判断访问到 endWord已经访问过的节点
                if (disEnd[nodeBegin] != Integer.MAX_VALUE) {
                    // TODO 因为添加了虚拟节点，所以得到的距离为实际最短路径长度的两倍。
                    //  同时并未计算起点对答案的贡献，所以我们应当返回距离的一半再加一的结果
                    return (disBegin[nodeBegin] + disEnd[nodeBegin]) / 2 + 1;
                }
                for (int it : edge.get(nodeBegin)) {
                    if (disBegin[it] == Integer.MAX_VALUE) {
                        disBegin[it] = disBegin[nodeBegin] + 1;
                        queBegin.offer(it);
                    }
                }
            }

            int queEndSize = queEnd.size();
            for (int i = 0; i < queEndSize; ++i) {
                int nodeEnd = queEnd.poll();
                if (disBegin[nodeEnd] != Integer.MAX_VALUE) {
                    return (disBegin[nodeEnd] + disEnd[nodeEnd]) / 2 + 1;
                }
                for (int it : edge.get(nodeEnd)) {
                    if (disEnd[it] == Integer.MAX_VALUE) {
                        disEnd[it] = disEnd[nodeEnd] + 1;
                        queEnd.offer(it);
                    }
                }
            }
        }
        return 0;
    }

    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            // 构造一个字符不同的相邻节点
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            // 图：双向边
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            // 复原单词
            array[i] = tmp;
        }
    }

    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            // 单词序列号，对应在edge的索引号
            edge.add(new ArrayList<Integer>());
        }
    }
}


