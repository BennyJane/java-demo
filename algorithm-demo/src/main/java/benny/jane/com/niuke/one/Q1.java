package benny.jane.com.niuke.one;

/**
 * n！后面有多少个0,6！=1*2*3*4*5*6=720.720后面有1个0，n=10000，求n！。
 */
public class Q1 {

    public static void main(String[] args) {
        Long ans = dfs(30);
        System.out.println(ans.toString());
    }

    private static long dfs(int n) {
        if (n == 1) {
            return 1;
        }
        return n * dfs(n - 1);
    }

}


