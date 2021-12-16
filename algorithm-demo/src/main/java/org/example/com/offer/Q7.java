package org.example.com.offer;

/**
 * 给定一个整数n，从1打印到n位最大的数 如1  打印1-9
 */
public class Q7 {
    public static void main(String[] args) {
        Q7 question = new Q7();
        question.method1(2);
    }

    private void method1(int n) {
        char[] array = new char[n];
        for (int i = 0; i < n; i++) {
            array[i] = '0';
        }

        while (increaseByOne(array)) {
            printNum(array);
            System.out.println();
        }

    }

    /**
     * 考虑到大数的问题，使用char数组模拟整数的增加，每次增加1
     *
     * @param array
     * @return
     */
    public boolean increaseByOne(char[] array) {
        int length = array.length;
        int takeOver = 0;   // 进位
        // 逆序迭代: 从个位开始
        // FIXME 优化，使用迭代器
        for (int i = length - 1; i >= 0; i--) {
            int sum = array[i] - '0' + takeOver;
            if (i == length - 1) {  // 个位数增加1
                sum++;
            }

            if (sum != 10) {
                array[i] = (char) ('0' + sum);
                break;
            }

            if (i == 0) {
                return false;   //最高位，结束
            } else {
                array[i] = '0'; // 当前位置设置为0
                takeOver = 1;   // 下一位加1
                sum = 0;    // ？
                // 继续循环
            }
        }
        return true;
    }

    static void printNum(char[] array) {
        int index = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == '0') {
                index++;
            } else {
                break;
            }
        }

        for (int i = index; i < array.length; i++) {
            System.out.print(array[i]);
        }
    }

}


