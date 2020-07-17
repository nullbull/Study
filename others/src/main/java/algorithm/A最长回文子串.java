package main.java.algorithm;

/**
 * @Auth justinniu
 * @Date 2018/10/16
 * @Desc
 */
public class A最长回文子串 {
    private static String LongestChild(String s) {
        int [][]a = new int[100][100];
        char[] c = s.toCharArray();
        int lenth = 1;
        for (int i = 0; i < s.length(); i++) {
            a[i][i] = 1;
            if (i < s.length() - 1 && c[i] == c[i+1]) {
                a[i][i+1] = 1;
                lenth = 2;
            }
        }

        int len = 3;
        for (len = 3; len < c.length; len++) {
            for (int i = 0; i + len - 1 < c.length; i++) {
                int j = i + len - 1;
                if (c[i] == c[j] && a[i+1][j-1] == 1) {
                    a[i][j] = 1;
                    lenth = len;
                }
            }
        }
        System.out.println(lenth);
        String ans = "";
        for (int i = 0; i < c.length; i++) {
            int j = i + lenth - 1;
            if (a[i][j] == 1) {
                ans = s.substring(i, j + 1);
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String a = "abbbbb";
        System.out.println(LongestChild(a));
    }
}
