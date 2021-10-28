package benny.jane.com.leetcode.tanxin;

/**
 * 44. 通配符匹配
 * https://leetcode-cn.com/problems/wildcard-matching/
 */
public class Q6 {
    public boolean isMatch(String s, String p) {
        char preSign = '.';
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur  = s.charAt(i);
            char P = p.charAt(start);
            if (P == '*') {
                preSign = '*';
            } else if (P == '?') {
                start++;
                preSign = '?';
            } else {

            }
        }
        return true;
    }
}


