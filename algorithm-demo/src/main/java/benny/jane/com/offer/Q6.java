package benny.jane.com.offer;

/**
 * 数值的整数次方
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * https://zhuanlan.zhihu.com/p/38715645
 */
public class Q6 {
    public static void main(String[] args) {
        Q6 question = new Q6();
        question.power(10, 5);
    }

    private double power(double base, int exp) {
        int n = exp;
        if (exp == 0) {
            // 当指数为0底数为0时，没有意义，返回0或者返回1都可以
            return 1;
        } else if (exp < 0) {
            if (base == 0) {
                throw new RuntimeException("分母不能为0");
            }
            n = -exp;
        }
        double res = PowerUnsignedExponent(base, n);
        return exp < 0 ? 1 / res : res;

    }

    public double PowerUnsignedExponent(double base, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return base;
        }

        double res = PowerUnsignedExponent(base,  n/2);
        res *= res;
        if (n % 2 == 1) {
            res *= base;
        }
        return res;
    }

    public double PowerUnsignedExponent2(double base, int n){
        if(n == 0)
            return 1;
        if(n == 1)
            return base;
        //递归
        double res = PowerUnsignedExponent(base, n>>1);
        res *= res;
        if((n & 0x1) == 1)
            res *= base;
        return res;
    }
}


