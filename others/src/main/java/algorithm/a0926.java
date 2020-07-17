package main.java.algorithm;

/**
 * @Auth justinniu
 * @Date 2018/9/26
 * @Desc
 */
public class a0926 {
    public static String zuichang(String a) {
        String ans = "";

        char s[] = a.toCharArray();
        int[][] dp = new int[100][100];
        int length = 0;
        for (int i = 0; i < s.length; i++) {
            dp[i][i] = 1;
            if (i < s.length - 1) {
                if (s[i] == s[i+1]) {
                    dp[i][i+1] = 1;
                    length = 2;
                }
            }
        }
        int l = 3 ;
        for (l = 3; l <= s.length; l++) {
            for (int i = 0; i + l - 1 < s.length; i++) {
                int j = i + l - 1;
                if (s[i] == s[j] && dp[i+1][j-1] == 1) {
                    dp[i][j] = 1;
                    length = l;
                }
            }
        }
        System.out.println(length);
        for (int i = 0; i < s.length; i++) {
            int j = i + length - 1;
            if (dp[i][j] == 1) {
                ans =  a.substring(i, j + 1);
                break;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
            String a = "abbbb";
        System.out.println(zuichang(a));

    }
}
