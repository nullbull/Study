package algorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * @Auth justinniu
 * @Date 2018/9/20
 * @Desc
 */
public class a0920 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        HashMap<Integer, Integer> time = new HashMap<>();
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("END")) {
                break;
            }
            String a[] = s.split("#");
            int n = Integer.valueOf(a[0]);
            int m = Integer.valueOf(a[1]);
            int temp = 0;
            int h = 1;
            while (m != 0) {
                int t = m % 10;
                temp += t * h;
                h = h * n;
                m = m / 10;
            }
            time.put(temp, time.get(temp) == null ? 1 : time.get(temp) + 1);
            result.put(s, temp);
        }
        int ans = 0;
        for (Integer i : time.keySet()) {
            if (time.get(i) == result.size()) {
                System.out.println("NONE");
                break;
            }
            if (time.get(i) == 1) {
                ans = i;
                break;
            }
        }
        for (String it : result.keySet()) {
            if (ans == result.get(it)) {
                System.out.println(it);
            }
        }
    }
}
