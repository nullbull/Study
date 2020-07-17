package main.java.leetcode;

public class A回文数字 {

    public static boolean isPalindrome(int x) {

        if (x < 0 ) return false;

        if ( x < 10) {
            return true;
        } else {
            int div = 1;
            int temp = x;
            while (temp >= 10 && div < x) {
                temp = temp / 10;
                div *= 10;
            }
            while ( x != 0) {
                int first = x / div;
                int last = x % 10;
                if (first != last){
                    return false;
                }
                x = (x % div) / 10;
                div = div / 100;
            }

        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(1000110001));
    }

}
