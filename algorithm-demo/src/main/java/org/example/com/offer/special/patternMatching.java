package org.example.com.offer.special;

import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;

//面试题 16.18. 模式匹配
public class patternMatching {

    boolean ans = false;

    public boolean patternMatching(String pattern, String value) {
        // vlaue = “” 错误，不能保证a与b，取不同的值； 不能直接跳过ab ba这两种情况
        if (pattern.length() == 1) {
            return true;
        }
        dfs(pattern, value, null, null, 0, 0);
        return ans;
    }

    // TODO 优先消耗模式pattern的字符
    private void dfs(String pattern, String value, String a, String b, int pIndex, int vIndex) {
        // 剪枝：确保a与b不等 或 已经找到答案
        if (a != null && a.equals(b) || ans) {
            return;
        }

        // TODO 终止条件：不能使用vIndex判断
        if (pIndex >= pattern.length()) {
            if (pIndex == pattern.length() && vIndex == value.length()) {
                ans = true;
            }
            return;
        }

        if (pattern.charAt(pIndex) == 'a') {
            // 没有确定a
            if (a == null) {
                // 需要考虑a为空字符串的情况；注意取等号
                for (int i = 0; i <= value.length() - vIndex; i++) {
                    a = value.substring(vIndex, vIndex + i);
                    dfs(pattern, value, a, b, pIndex + 1, vIndex + a.length());
                }
            } else {
                // 考虑索引越界；已经确定a，直接往下递归
                if (vIndex + a.length() <= value.length() && value.substring(vIndex, vIndex + a.length()).equals(a)) {
                    dfs(pattern, value, a, b, pIndex + 1, vIndex + a.length());
                }
            }
        } else {
            if (b == null) {
                for (int i = 0; i <= value.length() - vIndex; i++) {
                    b = value.substring(vIndex, vIndex + i);
                    dfs(pattern, value, a, b, pIndex + 1, vIndex + b.length());
                }
            } else {
                if (vIndex + b.length() <= value.length() && value.substring(vIndex, vIndex + b.length()).equals(b)) {
                    dfs(pattern, value, a, b, pIndex + 1, vIndex + b.length());
                }
            }
        }
    }

    private void dfs2(String pattern, String value, String a, String b, int pIndex, int vIndex) {
        if (a != null && a.equals(b) || ans) {
            return;
        }

        // TODO 终止条件：不能使用vIndex判断
        if (pIndex >= pattern.length()) {
            if (pIndex == pattern.length() && vIndex == value.length()) {
                ans = true;
            }
            return;
        }

        if (pattern.charAt(pIndex) == 'a') {
            if (a == null) {
                for (int i = 0; i <= value.length() - vIndex; i++) {
                    a = value.substring(vIndex, vIndex + i);
                    dfs(pattern, value, a, b, pIndex + 1, vIndex + a.length());
                }
            } else {
                if (vIndex + a.length() <= value.length() && value.substring(vIndex, vIndex + a.length()).equals(a)) {
                    dfs(pattern, value, a, b, pIndex + 1, vIndex + a.length());
                } else {
                    return;
                }
            }
        } else {
            if (b == null) {
                for (int i = 0; i <= value.length() - vIndex; i++) {
                    b = value.substring(vIndex, vIndex + i);
                    dfs(pattern, value, a, b, pIndex + 1, vIndex + b.length());
                }
            } else {
                if (vIndex + b.length() <= value.length() && value.substring(vIndex, vIndex + b.length()).equals(b)) {
                    dfs(pattern, value, a, b, pIndex + 1, vIndex + b.length());
                } else {
                    return;
                }
            }
        }
    }

    public boolean patternMatching2(String pattern, String value) {
        int count_a = 0, count_b = 0;
        for (char ch : pattern.toCharArray()) {
            if (ch == 'a') {
                ++count_a;
            } else {
                ++count_b;
            }
        }
        // 确保count_a 表示数量大的对象
        if (count_a < count_b) {
            int temp = count_a;
            count_a = count_b;
            count_b = temp;
            char[] array = pattern.toCharArray();
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] == 'a' ? 'b' : 'a';
            }
            pattern = new String(array);
        }
        if (value.length() == 0) {
            // FIXME 包含两种情况：只包含ab其一 或 两个都为0  ==》 只需要检测数量最小的b是否为0
            return count_b == 0;
        }
        if (pattern.length() == 0) {
            return false;
        }
        // 从0开始枚举。考虑a为空的情况
        for (int len_a = 0; count_a * len_a <= value.length(); ++len_a) {
            int rest = value.length() - count_a * len_a;
            if ((count_b == 0 && rest == 0) || (count_b != 0 && rest % count_b == 0)) {
                int len_b = (count_b == 0 ? 0 : rest / count_b);
                int pos = 0;
                boolean correct = true;
                String value_a = "", value_b = "";
                for (char ch : pattern.toCharArray()) {
                    if (ch == 'a') {
                        String sub = value.substring(pos, pos + len_a);
                        if (value_a.length() == 0) {
                            value_a = sub;
                        } else if (!value_a.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_a;
                    } else {
                        String sub = value.substring(pos, pos + len_b);
                        if (value_b.length() == 0) {
                            value_b = sub;
                        } else if (!value_b.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_b;
                    }
                }
                if (correct && !value_a.equals(value_b)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        patternMatching q = new patternMatching();
//        String a = "abba";
//        String b = "dogcatcatdog";


//        String a = "abba";
//        String b = "dogcatcatfish";

//        String a = "aaaa";
//        String b = "dogcatcatdog";

//        String a = "abba";
//        String b = "dogdogdogdog";

        String a = "bbbbbbbbbbbbbbabbbbb";
        String b = "ppppppppppppppjsftcleifftfthiehjiheyqkhjfkyfckbtwbelfcgihlrfkrwireflijkjyppppg";


//        String a = "bbba";
//        String b = "xxxxxx";
        q.patternMatching(a, b);
//        System.out.println(Character.isLetter('x'));
    }


    class Solution {
        int aCount;
        int bCount;

        public boolean patternMatching(String pattern, String value) {
            int patLen = pattern.length();
            int valLen = value.length();
            //1 pattern为""
            if (patLen == 0) {
                return valLen == 0;
            }
            //记录pattern中a,b的数量
            for (int i = 0; i < patLen; i++) {
                if (pattern.charAt(i) == 'a') {
                    aCount++;
                } else {
                    bCount++;
                }
            }
            //2 pattern不为""
            //2.1 value为"",判断pattern是否为一种字符组成
            if (valLen == 0) {
                return aCount == 0 || bCount == 0;
            }
            //2.2.1 value不为"",处理pattern只有一种字符串的情况
            if (aCount == 0) {
                return helper(value, bCount);
            } else if (bCount == 0) {
                return helper(value, aCount);
            }
            //2.2.2 value不为"",处理pattern中'a'或'b'可为""的情况
            if (helper(value, aCount)) {
                return true;
            }
            if (helper(value, bCount)) {
                return true;
            }
            //2.2.3 value不为"",枚举'a','b'所代表的字符串长度
            //使得aCount*aLen+bCount*bLen=valLen
            for (int aLen = 1; aLen * aCount <= valLen - bCount; aLen++) {
                //valLen减去所有a代表的字符串,个数不够为bCount的倍数
                if ((valLen - aLen * aCount) % bCount != 0) {
                    continue;
                }
                //通过aLen计算bLen
                int bLen = (valLen - aLen * aCount) / bCount;
                if (check(pattern, value, aLen, bLen)) {
                    return true;
                }
            }
            return false;
        }

        boolean helper(String val, int k) {
            int len = val.length();
            if (len % k != 0) {
                return false;
            }
            int perStrLen = len / k;
            for (int i = perStrLen; i < len; i += perStrLen) {
                if (!val.substring(i, i + perStrLen).equals(val.substring(0, perStrLen))) {
                    return false;
                }
            }
            return true;
        }

        boolean check(String pattern, String value, int aLen, int bLen) {
            String a = null;
            String b = null;
            for (int i = 0, j = 0; i < pattern.length(); i++) {
                //j每次移动aLen或bLen的长度
                if (pattern.charAt(i) == 'a') {
                    if (a == null) {
                        a = value.substring(j, j + aLen);
                    } else if (!value.substring(j, j + aLen).equals(a)) {
                        return false;
                    }
                    j += aLen;
                } else {
                    if (b == null) {
                        b = value.substring(j, j + bLen);
                    } else if (!value.substring(j, j + bLen).equals(b)){
                        return false;
                    }
                    j += bLen;
                }
            }
            return true;
        }
    }
}
