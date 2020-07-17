package main.java.algorithm;

import java.util.Scanner;

/**
 * @Auth justinniu
 * @Date 2018/9/29
 * @Desc
 */
public class A0929 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t[] = s.split(";");
        char a[] = t[0].toCharArray();
        char b[] = t[1].toCharArray();
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < a.length; i++) {
                if (b[i] == a[j]) {
                    while (b[i++] == a[j++]) {
                        break;
                    }
                    break;
                }
            }
            break;
        }
    }
}
