package main.java.leetcode;

public class A正则表达式 {

    public static boolean isMatch(String s, String p) {

        if (s == null || p == null) {
            return false;
        }

        if (s.equals("") && p.equals("")) {
            return true;
        }

        boolean[][] dp = new boolean[s.length() + 1 ][p.length() + 1];
        dp[0][0] = true;

       for (int i = 1; i <= p.length(); i++) {
           if (p.charAt(i - 1) == '*') {
               dp[0][i] = dp[0][i - 2];
           }
       }

        for (int j = 1; j <= p.length(); j++) {
            for (int i = 1; i <= s.length(); i++) {
                char n = p.charAt(j - 1);
                char m = s.charAt(i - 1);
                if (n == '*') {
                    if (p.charAt(j - 2) == '.' || m == p.charAt(j - 2)) {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    } else  {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    if (n == '.' || m == n) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a*"));
    }
}
