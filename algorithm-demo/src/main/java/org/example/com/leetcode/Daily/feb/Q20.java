package org.example.com.leetcode.Daily.feb;

public class Q20 {

    boolean ans = false;

    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        if (bits[n - 1] != 0) {
            return false;
        }

        dfs(bits, 0, n);
        return ans;
    }

    private void dfs(int[] arr, int index, int n) {
        if (index >= n - 1) {
            ans = true;
            return;
        }

        if (arr[index] == 0) {
            dfs(arr, index + 1, n);
        } else {
            if (index + 1 <= n - 2) {
                dfs(arr, index + 2, n);
            }
        }
    }

    public boolean isOneBitCharacter2(int[] bits) {
        int n = bits.length;
        if (bits[n - 1] != 0) {
            return false;
        }
        int index = 0;
        while (index < n - 1) {
            if (bits[index] == 0) {
                index++;
            } else {
                if (index + 1 < n - 1) {
                    index += 2;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isOneBitCharacter3(int[] bits) {
        int n = bits.length, i = 0;
        while (i < n - 1) {
            i += bits[i] + 1;
        }
        return i == n - 1;
    }

    public static void main(String[] args) {
        int x = 1, y = 2,z =3;
        System.out.println(y += z-- / ++x);
    }
}
