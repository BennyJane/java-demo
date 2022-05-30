package org.example.com.niuke.one;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
    static class B implements Comparable<B> {
        int m;
        int n;

        B(int m, int n) {
            this.m = m;
            this.n = n;
        }

        @Override
        public int compareTo(B o) {
            if (m > o.m) {
                return 1;
            }
            if (m < o.m) {
                return -1;
            }
            if (n > o.n) {
                return -1;
            }
            if (n < o.n) {
                return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] mm = in.nextLine().split(",");
        String[] nn = in.nextLine().split(",");

        int k = in.nextInt();
        B[] b = new B[mm.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = new B(Integer.parseInt(mm[i]), Integer.parseInt(nn[i]));
        }
        mm = null;
        nn = null;
        Arrays.sort(b);
        for (int i = 0; i < b.length; i++) {
            if (k >= b[i].m && b[i].n > b[i].m) {
                k += (b[i].n - b[i].m);
            }
        }
        System.out.print(k);
        in.close();

    }
}


class maxCha {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] str = in.nextLine().split(";");
        in.close();
        int num_eq = str[str.length - 1].split(",").length;  // 3,等式的数量
        int num_x = str[0].split(",").length;  // 5，系数和未知数的数量
        double[][] a= new double[num_eq][num_x];
        int[] x = new int[num_x];
        double[] b = new double[num_eq];
        String[] eq = new String[num_eq];
        int[] res = new int[num_eq];
        int max = 0;
        boolean flag = true;
        // 分别处理a,x,b,符号
        for (int i = 0; i < num_eq; i++) {  // 处理a
            String[] tmp1 = str[i].split(",");
            for (int j = 0; j < tmp1.length; j++) {
                a[i][j] = Double.valueOf(tmp1[j]);
            }
        }
        String[] tmp2 = str[num_eq].split(",");
        for (int i = 0; i < tmp2.length; i++) {  // 处理x
            x[i] = Integer.parseInt(tmp2[i]);
        }
        String[] tmp3 = str[num_eq + 1].split(",");
        for (int i = 0; i < tmp3.length; i++) {  // 处理b
            b[i] = Double.valueOf(tmp3[i]);
        }
        String[] tmp4 = str[num_eq + 2].split(",");
        for (int i = 0; i < tmp4.length; i++) {  // 处理符号
            eq[i] = tmp4[i];
        }
        for (int i = 0; i < num_eq; i++) {
            double tmp = 0.0;
            for (int j = 0; j < num_x; j++) {
                tmp += a[i][j] * x[j];
            }
            if ("<=".equals(eq[i])) {
                flag = tmp <= b[i] ? flag && true : flag && false;
            } else if ("<".equals(eq[i])) {
                flag = tmp < b[i] ? flag && true : flag && false;
            }else if ("=".equals(eq[i])) {
                flag = tmp == b[i] ? flag && true : flag && false;
            }else if (">=".equals(eq[i])) {
                flag = tmp >= b[i] ? flag && true : flag && false;
            }else if (">".equals(eq[i])) {
                flag = tmp > b[i] ? flag && true : flag && false;
            }
            res[i] =(int) ((tmp - b[i]) / 1);
        }
        for (int i = 0; i < num_eq; i++) {
            max = Math.max(max, res[i]);
        }
        System.out.println(flag + " " + max);
    }
}
