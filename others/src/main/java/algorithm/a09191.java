package main.java.algorithm;

/**
 * @Auth justinniu
 * @Date 2018/9/19
 * @Desc
 */
public class a09191 {
    public static String getString(String a, int n, String begin, String end) {
        int mark = 0;
        String temp = "";
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == begin.charAt(0)) {
                int count = 0;
                for (int j = 0; j < begin.length(); j++) {
                    if (a.charAt(i) == begin.charAt(j)) {
                        count++;
                        i++;
                    }
                }
                if (count == begin.length()) {
                    mark++;
                    i--;
                }
            }
            if (mark == n) {
                temp += a.charAt(i);
            }
        }
        int p = temp.indexOf(end);
        return temp.substring(0, p);
    }

    public static void main(String[] args) {
        String a = "sadsad123456cf";
        System.out.println(getString(a, 2, "sad", "cf"));
    }
}
