package main.java.algorithm;

import java.util.Scanner;

/**
 * @Auth justinniu
 * @Date 2018/9/21
 * @Desc
 */
public class a09212 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int dp[][] = new int[100][100];
        char a[] = s.toCharArray();
        int temp = 0;
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
            if (i < s.length() - 1) {
                if (a[i] == a[i + 1]) {
                    dp[i][i + 1] = 1;
                    temp = 2;
                }
            }
        }
        int L = 3;
        for (L = 3; L <= a.length; L++) {
            for (int i = 0; i + L - 1 < a.length; i++) {
                int j = i + L - 1;
                if (a[i] == a[j] && dp[i + 1][j - 1] == 1) {
                    dp[i][j] = 1;
                    temp = L;
                }
            }
        }
        System.out.println(temp);
        for (int i = 0; i < a.length; ++i) {
            int j = i + temp - 1;
            if (dp[i][j] == 1) {
                System.out.println(s.substring(i, j + 1));
                break;
            }
        }
    }
}
