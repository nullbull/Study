package main.java.algorithm;

/**
 * @Auth justinniu
 * @Date 2018/9/17
 * @Desc
 */
public class a0917 {
    public static void main(String[] args) {
        int a[][] = new int[5][5];
        int count = 1;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                a[i][j] = count++;
            int left = 0;
            int right = 4;
            int btm = 4;
            int top = 0;
            while (left <= right && top <= btm) {
                for (int i = left; i <= right; ++i) System.out.print(a[top][i] + " ");
                if (top < btm) {
                    for (int i = top + 1; i <= btm; i++) System.out.print(a[i][right] + " ");
                }
                if (left < right) {
                    for (int i = right - 1; i >= left; i--) System.out.print(a[btm][i] + " ");
                }
                if (left < right && top + 1 < btm) {
                    for (int i = btm - 1; i >= top + 1; i--) System.out.print(a[i][left] + " ");
                }
                left++; right--; top++; btm--;
            }
    }
}
