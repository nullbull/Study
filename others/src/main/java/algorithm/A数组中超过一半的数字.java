package algorithm;

/**
 * @Auth justinniu
 * @Date 2018/10/14
 * @Desc
 */
public class A数组中超过一半的数字 {
    public static int MoreThanHalfNum_Solution(int [] a) {
        int length = a.length;
        int half = length / 2;
        int[] b = new int[1000];
        for (int i = 0; i < a.length; i++) {
            b[a[i]]++;
        }
        for (int i = 0; i < 1000; i++) {
            if (b[i] > half) return i;
        }
        return 0;
    }
}

