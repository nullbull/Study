package algorithm;

import java.util.ArrayList;

/**
 * @Auth justinniu
 * @Date 2018/9/19
 * @Desc
 */
public class a0919 {

    public static ArrayList<ArrayList<Integer>> find(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (sum < 3) return result;
        int small = 1;
        int big = 2;
        int cursum = big + small;
        int mid = (sum + 1) / 2;
        while (small < mid) {
            while (cursum > sum && small < mid) {
                cursum = cursum - small;
                small++;
            }
            if (cursum == sum) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int i = small; i <= big; i++) {
                    temp.add(i);
                }
                result.add(temp);
                temp = null;
            }
            big++;
            cursum = cursum + big;
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> result = find(100);
        result.stream().forEach((arrayList) ->{
            for (Integer i : arrayList) {
                System.out.print(i + " ");
            }
            System.out.println();
        });
//        result.stream().forEach(arrayList -> arrayList.stream().forEach(System.out::println));
//
    }
}
