package algorithm;

/**
 * @Auth justinniu
 * @Date 2018/9/7
 * @Desc
 */
public class quickSort {
    int sort(int a[], int i, int j) {

        int p = i;
        int temp = a[i];
        while (i < j) {
            while (a[j] <= temp && i < j) j--;
            while (a[i] >= temp && i < j) i++;
            swap(a, i, j);
        }
        swap(a, i, p);
        return i;
    }
    void quickSortN(int a[], int length, int n) {
        if (n > length) return;
        int l = 0, r = length;
        while(l < r) {
            int index = sort(a, l, r);
            if(index > n) r = index-1;
            else if(index < n) l = index+1;
            else break;
        }

    }
    void qucikSort(int a[], int left, int right) {
        if (left > right) return;
        int index = sort(a, left, right);
        qucikSort(a, left, index - 1);
        qucikSort(a, index + 1, right);
    }
    void swap(int a[], int m, int n) {
        int temp = a[m];
        a[m] = a[n];
        a[n] = temp;
    }

    public static void main(String[] args) {
        quickSort qS = new quickSort();
        int []a = {13, 10 , 19, 20, 8, 7, 11, 99, 100, 23, 13, 129,132,12123, 123};
//        qS.quickSortN(a, 14, 3);
        qS.qucikSort(a, 0, 14);
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }
}
