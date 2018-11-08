package algorithm;

/**
 * @Auth justinniu
 * @Date 2018/8/20
 * @Desc
 */
public class Heap {
    public static int[] create(int a[], int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapAdjust(a, i, n);
        }
        n = n - 1;
        while (n >= 0) {
            swap(a, 0, n--);
            heapAdjust(a, 0, n);
        }

        return a;
    }

    public static void heapAdjust(int a[], int i, int n) {
        int left, right, j;
        while ((left = (i * 2 + 1)) < n) {
            right = left + 1;
            j = left;
            if((right < n) && (a[left] < a[right])) {
                j++;
            }
            if(a[i] < a[j]) swap(a, i, j);
            else break;
            i = j;
        }
    }
    public static void swap(int a[], int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void main(String[] args) {
        int []a = {13, 10 , 19, 20, 8, 7, 11};
        create(a, 7);
        for (int i = 0; i < 7; i++)
            System.out.println(a[i]);
    }
}
