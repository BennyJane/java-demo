package benny.jane.com.baseDemo;

public class WhileDemo {

    public static void main(String[] args) {
        funcEx();
    }

    static void funcEx() {
        String s = "100+20-30+15";
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {  // i++ 在for语块执行结束后，再执行加1操作
            int tempNum = arr[i] - '0';
            System.out.println("i :" + i);
            if (Character.isDigit(arr[i])) {
                // ++i 先执行加1，再进入循环
                while (++i < arr.length && Character.isDigit(arr[i])) {
                    tempNum = tempNum * 10 + (arr[i] - '0');
                    System.out.println("next i: " + i + ", " + tempNum);
                } i--;
            }
        }
    }
}
