package algorithm;

/**
 * @author: niuzhenhao
 * @date: 2019-10-22 15:16
 * @desc:
 */
public class A20191022 {
    public static int sum(int[] a) {
        int length = a.length;
        if (length == 0) {
            return 0;
        }
        int temp = a[length - 1];
        if (length == 1) {
            return temp;
        }
        int[] s = new int[length - 1];
        for (int i = 0; i < length - 1; i++) {
            s[i] = a[i];
        }
        return temp + sum(s);
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6};

        System.out.println(sum(a));
    }
}
