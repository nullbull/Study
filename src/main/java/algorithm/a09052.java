package algorithm;

import java.util.Arrays;

/**
 * @Auth justinniu
 * @Date 2018/9/5
 * @Desc
 */
public class a09052 {
    public String LeftRotateString(String s,int n) {
        if (s.isEmpty()) return s;
        n = n % (s.length());
        String a = s.substring(n, s.length());
        return a + s.substring(0, n);
    }
    public String ReverseSentence(String s) {
        if (s.isEmpty() || s.trim().isEmpty()) return s;
        String a[] = s.split(" ");
        String temp = "";
        for (int i = a.length - 1; i >= 0; i--) {
            temp += a[i];
            if (i != 0) temp += " ";
        }
        return temp;
    }
    public boolean isContinuous(int []n) {
        if (n.length == 0) return false;
        Arrays.sort(n);
        int z = 0;
        for (int i = 0; i < n.length; i++) {
            if (n[i] == 0) z++;
            else break;
        }
        if (z == 4) return true;
        else {
            int a[] = new int[5];
            int min = n[z];
            int count = 0;
            for (int i = 0; i < 5; i++) a[i] = min++;
            for (int i = 0; i < 5; i++) {
                for (int j = z; j < 5; j++) {
                    if (a[i] == n[j]) {
                        count++;
                        i++;
                    }
                }
            }
            if (count == 5 - z) return true;
        }

        return false;
    }

    public boolean duplicate(int n[],int length,int [] d) {
        byte bytes[] = new byte[100000];
        for (int i = 0; i < 100000; i++) bytes[i] = 0;
        for (int i = 0; i < length; i++) {
            bytes[n[i]]++;
            if (bytes[n[i]] == 2) {
                d[0] = n[i];
                break;
            }
        }
        if (d[0] == -1) return false;
        else return true;
    }

    public static void main(String[] args) {
        a09052 zwt = new a09052();
//        System.out.println(zwt.LeftRotateString("abcXYZdef", 13));
//        System.out.println(zwt.ReverseSentence("I am a student."));
//        System.out.println( "|"  + "                 ".trim() + "|");
        int a[] = {1, 0, 0, 1, 0};
        System.out.println(zwt.isContinuous(a));
    }
}
