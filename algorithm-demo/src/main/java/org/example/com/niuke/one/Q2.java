package org.example.com.niuke.one;

public class Q2 {
    public static void main(String[] args) {
        String s = "sdfsf\\dfs";
    }
}


//class Main {
//    // 线段树
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//
//        while (sc.hasNextLine()) {
//            String firstInput = sc.nextLine();
//            String[] arr = firstInput.split(" ");
//            int n = Integer.parseInt(arr[0]);
//            int m = Integer.parseInt(arr[1]);
//            String[] strArr = sc.nextLine().split(" ");
//            int[] data = new int[n];
//            int[] max = new int[(n + 99) / 100];
//
//            for (int i = 0; i < n; i++) {
//                int c = Integer.parseInt(strArr[i]);
//                data[i] = c;
//                //System.out.println("array: " + c);
//                // 更新区间最大值
//                int maxIndex = i / 100;
//                max[maxIndex] = Math.max(c, max[maxIndex]);
//            }
//
//
//            while (m > 0) {
//                String secondInput = sc.nextLine();
//                String[] mArr = secondInput.split(" ");
//                String action = mArr[0];
//                int a = Integer.parseInt(mArr[1]);
//                int b = Integer.parseInt(mArr[2]);
//
//                //System.out.println(a + "_" + b + "_" + action);
//                if (action.equals("Q")) {
//                    if (a > b) {
//                        int tmp = a;
//                        a = b;
//                        b = tmp;
//                    }
//
//
//                    a -= 1;
//                    b -= 1;
//                    int m1 = a / 100;
//                    int m2 = b / 100;
//                    if (m1 == m2) {
//                        int res = 0;
//                        for (int i = a; i <= b; i++) {
//                            res = Math.max(res, data[i]);
//                        }
//                        System.out.println(res);
//                    } else {
//                        int res = 0;
//                        for (int i = a; i < 100 * (m1 + 1); i++) {
//                            res = Math.max(res, data[i]);
//                        }
//                        for (int i = m1 + 1; i < m2; i++) {
//                            res = Math.max(res, max[i]);
//                        }
//                        for (int i = m2 * 100; i <= b; i++) {
//                            res = Math.max(res, data[i]);
//                        }
//                        System.out.println(res);
//                    }
//                } else {
//                    data[a - 1] = b;
//                    // 更新区间最大值
//                    int maxIndex = (a - 1) / 100;
//                    max[maxIndex] = Math.max(b, max[maxIndex]);
//                }
//                m--;
//            }
//
//        }
//
//    }
//}


class Solution {
    public String solve(String s, String t) {
        if (s.length() == 0) return t;
        if (t.length() == 0) return s;

        int isNeg1 = 1;
        if (s.charAt(0) == '-') {
            isNeg1 = -1;
            s = s.substring(1);
        }
        int isNeg2 = 1;
        if (t.charAt(0) == '-') {
            isNeg2 = -1;
            t = t.substring(1);
        }

        // 判断符合是否不同
        boolean isDiff = isNeg1 * isNeg2 < 0;

        // TODO 核心：需要确定两个数大小关系，减法运算需要较大值 - 较小值
        if (!isDiff) {
            String ans = addSame(s, t);
            if (isNeg1 < 0) return "-" + ans;
            return ans;
        }

        int compareResult = compareStr(s, t);

        if (compareResult == 0) {
            return "0";
        } else if (compareResult > 0) {
            String ans = addOther(s, t);
            if (isNeg1 < 0) return "-" + ans;
            return ans;
        } else {
            String ans = addOther(t, s);
            if (isNeg2 < 0) return "-" + ans;
            return ans;
        }
    }

    private String addSame(String s, String t) {
        int n = s.length();
        int m = t.length();

        int add = 0;
        int right1 = n - 1, right2 = m - 1;
        StringBuilder sb = new StringBuilder();
        while (right1 >= 0 || right2 >= 0) {
            int v1 = right1 >= 0 ? s.charAt(right1) - '0' : 0;
            int v2 = right2 >= 0 ? t.charAt(right2) - '0' : 0;

            // 结合add，计算当前
            int sum = v1 + v2 + add;
            int nxt = sum % 10;
            add = sum / 10;
            sb.append(nxt);

            right1--;
            right2--;
        }

        if (add > 0) {
            sb.append(add);
        }
        // reverse() 方法，逆向排序
        return sb.reverse().toString();
    }

    private String addOther(String larger, String lower) {
        int n = larger.length();
        int m = lower.length();

        int add = 0;
        int right1 = n - 1, right2 = m - 1;
        StringBuilder sb = new StringBuilder();
        while (right1 >= 0 || right2 >= 0) {
            int v1 = right1 >= 0 ? larger.charAt(right1) - '0' : 0;
            int v2 = right2 >= 0 ? lower.charAt(right2) - '0' : 0;

            // 结合add，计算当前
            int sum = 0;
            if (v1 + add >= v2) {
                sum = v1 - v2 + add;
            } else {
                sum = 10 + v1 - v2 + add;
                add = -1;
            }
            int nxt = sum % 10;
            sb.append(nxt);

            right1--;
            right2--;
        }

        // TODO 大数 - 小数，可能存在头部为0
        // reverse() 方法，逆向排序
        String res = sb.reverse().toString();
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) != '0') {
                res = res.substring(i);
                break;
            }
        }
        return res;
    }

    private int compareStr(String s, String t) {
        int n = s.length(), m = t.length();
        if (n == m) {
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    return s.charAt(i) - t.charAt(i);
                }
            }
            return 0;
        } else if (n > m) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        System.out.println(s.solve("-1", "-100"));
//        System.out.println(s.solve("-1", "100"));
//        System.out.println(s.solve("1", "100"));
//        System.out.println(s.solve("1", "-100"));
//
//
//        System.out.println(s.solve("-1", "-99"));
        System.out.println(s.solve("1000", "-999"));
        System.out.println(s.solve("-999", "-999"));
        System.out.println(s.solve("999", "-999"));

    }
}