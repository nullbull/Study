package leetcode;

public class A整数反转 {

    public static int reverse(int x) {

        long xx = x;
        if (x == 0 ) {
            return 0;
        }
        boolean isF = false;
        if (xx < 0) {
            xx = -xx;
            isF = true;
        }
        String s = "";
        while ( xx != 0) {
             s = s + (xx % 10);
            xx = xx / 10;
        }
        if (isF) {
            s = "-" + s;
        }
        long l = Long.parseLong(s);
        if (l > Integer.MAX_VALUE || l < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) l;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
    }
}
