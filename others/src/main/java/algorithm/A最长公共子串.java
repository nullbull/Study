package main.java.algorithm;

/**
 * @Auth justinniu
 * @Date 2018/10/16
 * @Desc
 */
public class A最长公共子串 {
    public static int lcs(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int ans = 0;
        int c[][] = new int[100][100];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i > 0 && j > 0 &&s1.charAt(i-1) == s2.charAt(j-1) ) {
                    c[i][j] = c[i-1][j-1] + 1;
                    ans = Math.max(ans, c[i][j]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String a = "abcddef";
        String b = "cccabcdde";
        System.out.println(lcs(a, b));
    }
}
