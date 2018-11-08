package algorithm;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * @Auth justinniu
 * @Date 2018/8/9
 * @Desc
 */
public class Main {
    public static String ReverseSentence(String str) {
        if (str.trim().equals("") || str == null)
            return str;
        String[] a = new String[100];
        a = str.split(" ");
        String temp = "";
        for (int i = a.length - 1;  i >=0; i--) {
            temp += a[i];
            if(i != 0) temp += " ";
        }
        return temp;
    }

    public static int lastWordLength(String temp) {

        String a[] = new String[5000];
        a = temp.split(" ");
        String s = a[a.length-1];
        return s.length();
    }
    public static void countOfChar() {
        Scanner sc = new Scanner(System.in);
        String temp = sc.next();
        temp = temp.toUpperCase();
        char c = sc.next().toUpperCase().charAt(0);
        int sum = 0;
        for (int i = 0; i < temp.length(); i++)
            if (temp.charAt(i) == c)  sum++;
        System.out.println(sum);
    }
    public static int StrToInt(String s) {
        s = s.trim();
        int val = 0;
        if (s.isEmpty()) return val;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) < '0' ||s.charAt(i) > '9' )
                return val;
        }
        char c = s.charAt(0);
        int x = 1;
        if (c < '0' || c > '9') {

        System.out.println(c);
            if (c == '+') x = 1;
            else if (c == '-') x = -1;
            else return val;
            s = s.substring(1, s.length());
            System.out.println(s);
        }



        for (int i = s.length() - 1; i >= 0; i--) {
            int temp = s.charAt(i) - '0';
//            System.out.println(temp);
            val += temp * x;
            x = x * 10;
        }
        System.out.println(val);
        return val;
    }
    public static int ssss(String a) {
        int max = -1;
        int temp = 0;
        for (int i = 0; i < a.length(); i++) {
            if(a.charAt(i) == '(') {
                temp++;
                max = Math.max(temp, max);

            }
            else temp--;
        }
        return max;
    }
    public static int Roma(String a) {
 /*       HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);*/
        int sum = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            System.out.println(i);
            System.out.println(sum);
            if ( i != 0 && a.charAt(i) == 'V' && a.charAt(i - 1) == 'I') { sum += 4; i--;}
            else if (i != 0 && a.charAt(i) == 'X' && a.charAt(i - 1) == 'I') { sum += 9; i--;}
            else if (i != 0 && a.charAt(i) == 'L' && a.charAt(i - 1) == 'X') { sum += 40; i--;}
            else if (i != 0 && a.charAt(i) == 'C' && a.charAt(i - 1) == 'X') { sum += 90; i--;}
            else if (i != 0 && a.charAt(i) == 'D' && a.charAt(i - 1) == 'C') { sum += 400; i--;}
            else if (i != 0 && a.charAt(i) == 'M' && a.charAt(i - 1) == 'C') { sum += 900; i--;}
            else if (a.charAt(i) == 'V') sum += 5;
            else if (a.charAt(i) == 'X') sum += 10;
            else if (a.charAt(i) == 'L') sum += 50;
            else if (a.charAt(i) == 'C') sum += 100;
            else if (a.charAt(i) == 'D') sum += 500;
            else  if(a.charAt(i) == 'M') sum += 1000;
            else if (a.charAt(i) == 'I') sum += 1;
        }
        return sum;

    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        String s = "-123";
//        System.out.println(StrToInt(s));
//        ExecutorService service = Executors.newCachedThreadPool();
//        Callable<Integer> n = () -> {return 1;};
//        Future<Integer> future = service.submit(n);
//        System.out.println(future.get());
//        System.out.println(Roma("MCMXCIV"));
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        System.out.println(ssss(a));
    }

    

}
