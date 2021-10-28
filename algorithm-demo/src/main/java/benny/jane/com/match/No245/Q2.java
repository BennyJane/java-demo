package benny.jane.com.match.No245;

/**
 * 5786. 可移除字符的最大数目
 */
public class Q2 {
    // TODO 超出时间范围
    public int maximumRemovals(String s, String p, int[] removable) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < removable.length; i++) {
            StringBuffer res = new StringBuffer();
            boolean[] isDel = new boolean[s.length()];
            for (int j = 0; j <= i; j++) {
                isDel[removable[j]] = true;
            }
            for (int index = 0; index < s.length(); index++) {
                if (!isDel[index]) {
                    res.append(chars[index]);
                }
            }
            System.out.println(res.toString());
            // 判断p是否仍然是ori的子序列
            int start = 0;
            for (char c : p.toCharArray()) {
                while (start < res.length()) {
                    if (c == res.charAt(start)) {
                        break;
                    }
                    start++;
                }
                if (start > res.length() - 1) {
                    return i;
                }
                start++;
            }
        }
        return removable.length;
    }

    public static void main(String[] args) {
        Q2 q = new Q2();
//        String s = "abcacb";
//        String p = "ab";
//        int[] nums = {3, 1, 0};
//        int res = q.maximumRemovals(s, p, nums);

//        String s = "abcbddddd";
//        String p = "abcd";
//        int[] nums = {3,2,1,4,5,6};
//        int res = q.maximumRemovals(s, p, nums);

        String s = "qlevcvgzfpryiqlwy";
        String p = "qlecfqlw";
        int[] nums = {12,5};
        int res = q.maximumRemovals(s, p, nums);

        System.out.println(res);
    }
}


