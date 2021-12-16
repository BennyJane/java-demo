package org.example.com.offer;

public class Q1 {
    public static void main(String[] args) {
        int[][] array1 = {
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        int target1 = 10;
//        int target1 = 20;

        boolean res;
        res = contains(target1, array1);
        System.out.println(res);

        res = contains2(target1, array1);
        System.out.println(res);

        int[][] array2 = {
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 11, 13, 15}
        };
        int target2 = 6;
        res = contains(target2, array2);
        System.out.println(res);

        res = contains2(target2, array2);
        System.out.println(res);
    }

    private static boolean contains(int key, int[][] array) {
        // 效率不高
        for (int[] oneArray : array) {
            if (oneArray[oneArray.length - 1] > key) {
                for (int val : oneArray) {
                    if (val == key) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean contains2(int key, int[][] array) {
        int rowLength = array.length;
        int colLength = array[0].length - 1;

        int row = 0;
        // && 与 || 都不改变算法的正确性
        // 影响执行的效率： && 效率更高，遍历的数据更少
        while (row < rowLength && colLength >= 0) {
            int colLastVal = array[row][colLength];
            if (colLastVal == key) {
                return true;
            } else if (colLastVal < key) {
                row++;
            } else {
                colLength--;
            }
        }
        return false;
    }
}
