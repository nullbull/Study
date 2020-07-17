package main.java.algorithm;

/**
 * @Auth justinniu
 * @Date 2018/9/23
 * @Desc
 */

public class a0923Test {
   static class test{
        public final static String a = new String("23");
        public static int b = 4;
        static {
            System.out.println("init");
        }

    }

    public static void main(String[] args) {
        System.out.println(test.a);
    }
}
