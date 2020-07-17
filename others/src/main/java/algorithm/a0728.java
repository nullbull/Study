package algorithm;

import java.util.Scanner;

public class a0728 {
    static int x[] = new int[100];
    public static boolean has(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            while (a[i] != i) {
                if(a[i] == a[a[i]])
                {
                    x[0] = a[i];
                    return true;
                }
                int temp = a[a[i]];
                a[a[i]] = a[i];
                a[i] =temp;

            }

        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[10];
        int m = 0;
        while (sc.hasNext()) {
            a[m++] = sc.nextInt();
            if (m==n)
                sc.close();
        }

        System.out.println(has(a, n));
    }
}
