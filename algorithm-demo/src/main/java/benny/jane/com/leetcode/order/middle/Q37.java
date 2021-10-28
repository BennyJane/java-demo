package benny.jane.com.leetcode.order.middle;

/**
 * 794. 有效的井字游戏
 * https://leetcode-cn.com/problems/valid-tic-tac-toe-state/
 */
public class Q37 {
    /*
     条件：
     1. X的个数：  等于O的个数，或者 多一个
     2. 3个相同符号的情况不能两方同时出现；同一符号也不允许出现没有交叉的多次成立的情况
     */
    public boolean validTicTacToe(String[] board) {
        int xCount = 0;
        int oCount = 0;
        for (String row : board) {
            for (char c : row.toCharArray()) {
                if (c == 'X') {
                    xCount++;
                }
                if (c == 'O') {
                    oCount++;
                }
            }
        }

        // 优先处理反面情况
        if (oCount != xCount && oCount != xCount - 1) {
            return false;
        }
        // X赢时，xCount应该多1，两者冲突
        if (oCount == xCount && win(board, 'X')) {
            return false;
        }
        if (win(board,'O') && oCount != xCount) {
            return false;
        }

        return true;
    }

    public boolean win(String[] B, char P) {
        for (int i = 0; i < 3; i++) {
            // 检测列是否存在成立情况
            if (P == B[0].charAt(i) && P == B[1].charAt(i) && P == B[2].charAt(i)) {
                return true;
            }
            // 检测行
            if (P == B[i].charAt(0) && P == B[i].charAt(1) && P == B[i].charAt(2)) {
                return true;
            }
        }
        if (P == B[0].charAt(0) && P == B[1].charAt(1) && P == B[2].charAt(2)) {
            return true;
        }
        if (P == B[0].charAt(2) && P == B[1].charAt(1) && P == B[2].charAt(0)) {
            return true;
        }
        return false;
    }
}


