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
            if(m > o.m) {
                return 1;
            }
            if(m < o.m) {
                return -1;
            }
            if(n > o.n) {
                return -1;
            }
            if(n < o.n) {
                return 1;
            }
            return 0;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String [] mm = in.nextLine().split(",");
        String [] nn = in.nextLine().split(",");

        int k = in.nextInt();
        B [] b = new B [mm.length];
        for(int i = 0; i < b.length; i ++) {
            b[i] = new B(Integer.parseInt(mm[i]), Integer.parseInt(nn[i]));
        }
        mm = null;
        nn = null;
        Arrays.sort(b);
        for(int i = 0; i < b.length; i ++) {
            if(k >= b[i].m && b[i].n > b[i].m) {
                k += (b[i].n - b[i].m);
            }
        }
        System.out.print(k);
        in.close();

    }
}

