package algorithm;

import java.util.ArrayList;

/**
 * @Auth justinniu
 * @Date 2018/9/5
 * @Desc
 */
public class a0905 {
    public ArrayList<Integer> FindNumbersWithSum(int [] a, int sum) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (a.length == 0) return ans;
        int max = a[a.length - 1];
        int min = a[0];
        int result[] = new int[100000];
        for (int i = 0; i < a.length; i++) {
            int temp = sum - a[i];
            if (temp > max) break;
            if (temp < min) break;
            if (find(a, temp)) result[a[i]] = temp;
        }
        int temp = 100000;
        int v = 0;
        for (int i = 0; i < result.length; i++){
            if (result[i] != 0) {
                int cj = i * (sum - i);
                if(temp > cj) {
                    temp = cj;
                    v = i;
                }
            }
        };
        if (v != 0) {
            ans.add(v);
            ans.add(sum - v);
        }

        return ans;
    }
    public boolean find(int a[], int k) {
        int i = 0;
        int j = a.length - 1;
        while (i <= j) {
            int cen = (i + j) / 2;
            if (a[cen] > k) j = cen - 1;
            if (a[cen] < k) i = cen + 1;
            if (a[cen] == k) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        a0905 zwt = new a0905();
        int a[] = {1, 2, 4, 7, 11, 16};
        System.out.println(zwt.FindNumbersWithSum(a, 17));
    }
}
