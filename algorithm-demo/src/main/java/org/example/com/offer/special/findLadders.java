package org.example.com.offer.special;

import javafx.util.Pair;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.*;

public class findLadders {
    // 为每个单词添加序列号码
    Map<String, Integer> wordId = new HashMap<String, Integer>();
    // 建立图
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;
    List<List<String>> result = new ArrayList<>();
    int minPath = Integer.MAX_VALUE;

    public List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) {
        //创建图
        for (String word : wordList) {
            addEdge(word);
        }
        // 添加起始点
        addEdge(beginWord);
        // 图中不包含endWord 直接返回
        if (!wordId.containsKey(endWord)) {
            return result;
        }

        Map<Integer, String> idWord = new HashMap<>();
        for (String w : wordId.keySet()) {
            idWord.put(wordId.get(w), w);
        }


        boolean[] visited = new boolean[nodeNum];
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        // 从beginWord开始搜索
        Queue<Pair<Integer, List<String>>> queBegin = new LinkedList<>();
        Pair<Integer, List<String>> pair = new Pair<>(beginId, new ArrayList<>());
        pair.getValue().add(beginWord);
        queBegin.offer(pair);
        visited[beginId] = true;

        while (!queBegin.isEmpty()) {
            int size = queBegin.size();
            for (int i = 0; i < size; i++) {
                Pair<Integer, List<String>> curPair = queBegin.poll();
                if (curPair.getValue().size() > minPath) {
                    continue;
                }
                Integer curId = curPair.getKey();
                visited[curId] = true;
                if (curId == endId) {
                    List<String> paths = curPair.getValue();
                    paths.add(endWord);
                    if (paths.size() < minPath) {
                        result.clear();
                        result.add(new ArrayList<>(paths));
                    } else if (paths.size() == minPath) {
                        result.add(new ArrayList<>(paths));
                    }
                } else {
                    List<String> curPaths = curPair.getValue();
                    for (Integer nxtId : edge.get(curId)) {
                        if (visited[nxtId]) {
                            continue;
                        }
                        String nxtWord = idWord.get(nxtId);
                        curPaths.add(nxtWord);
                        Pair<Integer, List<String>> nxtPair = new Pair<>(beginId, new ArrayList<>(curPaths));
                        queBegin.add(nxtPair);
                        curPaths.remove(nxtWord);
                    }
                }
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for (List<String> list : result) {
            List<String> temp = new ArrayList<>();
            for (String s : list) {
                if (!s.contains("*")) {
                    temp.add(s);
                }
            }
        }


        return ans;
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


    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        // 因为需要快速判断扩展出的单词是否在 wordList 里，因此需要将 wordList 存入哈希表，这里命名为「字典」
        Set<String> dict = new HashSet<>(wordList);
        // 特殊用例判断
        if (!dict.contains(endWord)) {
            return res;
        }

        dict.remove(beginWord);

        // 第 1 步：广度优先遍历建图
        // 记录扩展出的单词是在第几次扩展的时候得到的，key：单词，value：在广度优先遍历的第几层
        Map<String, Integer> steps = new HashMap<>();
        steps.put(beginWord, 0);
        // 记录了单词是从哪些单词扩展而来，key：单词，value：单词列表，这些单词可以变换到 key ，它们是一对多关系
        Map<String, List<String>> from = new HashMap<>();
        int step = 1;
        boolean found = false;
        int wordLen = beginWord.length();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                char[] charArray = currWord.toCharArray();
                // 将每一位替换成 26 个小写英文字母
                for (int j = 0; j < wordLen; j++) {
                    char origin = charArray[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        charArray[j] = c;
                        String nextWord = String.valueOf(charArray);
                        if (steps.containsKey(nextWord) && step == steps.get(nextWord)) {
                            from.get(nextWord).add(currWord);
                        }
                        if (!dict.contains(nextWord)) {
                            continue;
                        }
                        // 如果从一个单词扩展出来的单词以前遍历过，距离一定更远，为了避免搜索到已经遍历到，且距离更远的单词，需要将它从 dict 中删除
                        dict.remove(nextWord);
                        // 这一层扩展出的单词进入队列
                        queue.offer(nextWord);

                        // 记录 nextWord 从 currWord 而来
                        from.putIfAbsent(nextWord, new ArrayList<>());
                        from.get(nextWord).add(currWord);
                        // 记录 nextWord 的 step
                        steps.put(nextWord, step);
                        if (nextWord.equals(endWord)) {
                            found = true;
                        }
                    }
                    charArray[j] = origin;
                }
            }
            step++;
            if (found) {
                break;
            }
        }

        // 第 2 步：深度优先遍历找到所有解，从 endWord 恢复到 beginWord ，所以每次尝试操作 path 列表的头部
        if (found) {
            Deque<String> path = new ArrayDeque<>();
            path.add(endWord);
            dfs(from, path, beginWord, endWord, res);
        }
        return res;
    }

    public void dfs(Map<String, List<String>> from, Deque<String> path, String beginWord, String cur, List<List<String>> res) {
        if (cur.equals(beginWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (String precursor : from.get(cur)) {
            path.addFirst(precursor);
            dfs(from, path, beginWord, precursor, res);
            path.removeFirst();
        }
    }

}
