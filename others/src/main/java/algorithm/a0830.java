package algorithm;

/**
 * @Auth justinniu
 * @Date 2018/8/30
 * @Desc
 */
public class a0830 {
     static int sum = 0;
    public static int sum(int n) {
        if (n == 1) {
            sum += n;
            return sum;
        }
        return sum(n - 1);

    }

    public static void main(String[] args) {
        System.out.println(sum(5));
    }
}
