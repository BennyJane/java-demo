package org.example.com.niuke.one;

import java.util.*;

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


class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Integer> map = new HashMap<>();

        List<String> order = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // TODO　确认分割符号
            String[] array = line.split("\\.");
            String fileNo = array[array.length - 1];
            map.put(fileNo, map.getOrDefault(fileNo, 0) + 1);

            order.add(fileNo);
        }


        Collections.sort(order,
                (a, b) -> {
                    return map.get(b) - map.get(a);
                }

        );

        int limit = Math.min(8, order.size());

        for (int i = 0; i < limit; i++) {
            String fileNumber = order.get(i);
            String[] arr = fileNumber.split(" ");
            String file = arr[0];
            if (file.length() > 16) {
                int len = file.length();
                file = file.substring(len - 16, len);
            }
            String number = arr[1];

            String res = file + " " + number + " " + map.get(fileNumber);

            System.out.println(res);
        }
    }
}