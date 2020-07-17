package algorithm;

/**
 * @Auth justinniu
 * @Date 2018/10/14
 * @Desc
 */
public class mergeSort {
    public static void sort(int a[], int left, int right, int temp[]) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(a, left, mid, temp);
            sort(a, mid + 1, right, temp);
            merge(a, left, mid, right, temp);
        }
    }
    public static void merge(int a[], int left, int mid, int right, int temp[]) {
        int i = left;
        int j = mid + 1;
        int p = 0;
        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                temp[p++] = a[i++];
            } else {
                temp[p++] = a[j++];
            }
        }
        while (i <= mid) temp[p++] = a[i++];
        while (j <= right) temp[p++] = a[j++];
        int count = 0;
        while (left <= right) a[left++] = temp[count++];
    }

    public static void main(String[] args) {
        int a[] = {23, 7, 101, 13, 3};
        int temp[] = new int[10];
        sort(a, 0, 4, temp);
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }
}
