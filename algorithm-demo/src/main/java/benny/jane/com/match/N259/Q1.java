package benny.jane.com.match.N259;

public class Q1 {
    public int finalValueAfterOperations(String[] operations) {
        int ans =0 ;
        for (String s : operations) {
            if (s.contains("+")) {
                ans++;
            } else {
                ans--;
            }
        }
        return ans;
    }
}
