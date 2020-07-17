package main.java.algorithm;

/**
 * @Auth justinniu
 * @Date 2018/8/10
 * @Desc
 */
public class a0810 {
    public static int longtestChild(String a, String b) {
        int max = -1;
        int dp[][] = new int[60][60];
        for(int i = 0; i < a.length(); i++) {
            for(int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    if(i > 0 && j > 0) dp[i][j] = dp[i-1][j-1] + 1;
                    else dp[i][j] = 1;
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }
    public static String test(String a) {
        int max = -1;
        int start = 0;
        int dp[][] = new int[100][100];

        dp[0][0] = 0;
        for (int i = 0; i < a.length(); i++) dp[i][i] = 1;



        for (int i = 0; i < a.length(); i++)
            for (int j = i - 1; j >= 0; j--) {
                if (a.charAt(i) == a.charAt(j)) {
                    if(i - j == 1) dp[i][j] = 2;
                    else {
                        if (dp[i-1][j+1] > 0) dp[i][j] = dp[i-1][j+1] + 2;
                    }
                }
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    start = j;
                }

            }
        System.out.println(max);
        System.out.println(start);
        return a.substring(start, start + max);
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String a = sc.nextLine();
//        String b = sc.nextLine();
//        if (a.length() < b.length()) {
//            String temp = a;
//            a = b;
//            b = temp;
//        }
//
//        System.out.println(longtestChild(a, b));
        boolean b = true ? false : true == true ? false : true;
        System.out.println(b);
        System.out.println(test("aaaaaaaaaaa"));
    }
}
