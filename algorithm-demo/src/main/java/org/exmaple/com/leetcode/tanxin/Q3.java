package org.exmaple.com.leetcode.tanxin;

/**
 * https://leetcode-cn.com/problems/minimum-domino-rotations-for-equal-row/
 * 1007. 行相等的最少多米诺旋转
 */
public class Q3 {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int resA = tops[0];
        int countA = 0;
        boolean aRes = true;
        int resB = bottoms[0];
        int countB = 0;
        boolean bRes = true;
        int len = tops.length;

        int ans = Integer.MAX_VALUE;

        // 处理取resA的情况： 分两种情况
        // 将所有相等值移动到A数组；或 将相等值移动到B数组
        for (int i = 0; i < len; i++) {
            int a = tops[i];
            int b = bottoms[i];
            if (a != resA && b != resA) {
                aRes = false;
                break;
            }
            // 需要调换位置
            if (a != resA && b == resA) {
                countA++;
            }

            if (a == resA && b != resA) {
                countB++;
            }
        }

        if (aRes) {
            ans = Math.min(countA, countB);
        }

        countA = 0;
        countB = 0;
        for (int i = 0; i < len; i++) {
            int a = tops[i];
            int b = bottoms[i];
            if (a != resB && b != resB) {
                bRes = false;
                break;
            }
            if (a == resB && b == resB) {
                continue;
            }
            // 需要调换位置
            if (b == resB) {
                countA++;
            }

            if (a == resB) {
                countB++;
            }
        }

        if (bRes) {
            ans = Math.min(countA, countB);
        }
        if (!bRes && !aRes) {
            return -1;
        }
        return ans;
    }

    public int check(int x, int[] A, int[] B, int n) {
        // how many rotations should be done
        // to have all elements in A equal to x
        // and to have all elements in B equal to x
        int rotations_a = 0, rotations_b = 0;
        for (int i = 0; i < n; i++) {
            // rotations coudn't be done
            if (A[i] != x && B[i] != x) return -1;
                // A[i] != x and B[i] == x
            else if (A[i] != x) rotations_a++;
                // A[i] == x and B[i] != x
            else if (B[i] != x) rotations_b++;
        }
        // min number of rotations to have all
        // elements equal to x in A or B
        return Math.min(rotations_a, rotations_b);
    }

    public int minDominoRotations2(int[] A, int[] B) {
        int n = A.length;
        int rotations = check(A[0], B, A, n);
        // If one could make all elements in A or B equal to A[0]
        if (rotations != -1 || A[0] == B[0]) return rotations;
            // If one could make all elements in A or B equal to B[0]
        else return check(B[0], B, A, n);
    }
}


