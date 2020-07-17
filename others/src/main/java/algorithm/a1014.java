package main.java.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auth justinniu
 * @Date 2018/10/14
 * @Desc
 */
public class a1014 {
    public static HashMap<Integer, ArrayList>  get(int a[], int n) {
        HashMap<Integer, ArrayList> ans = new HashMap<>();
        ArrayList<Integer> index1 = new ArrayList<>();
        ArrayList<Integer> index2 = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i] + a[j] == n) {
                    index1.add(i);
                    index2.add(j);
                }
            }
        }
        ans.put(1, index1);
        ans.put(2, index2);
        return ans;
    }
    public static HashMap<Integer, ArrayList>  get2(int a[], int n) {
        HashMap<Integer, ArrayList> ans = new HashMap<>();
        ArrayList<Integer> index1 = new ArrayList<>();
        ArrayList<Integer> index2 = new ArrayList<>();
        HashMap<Integer, Queue> temp = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (null != temp.get(n-a[i])) {
                temp.get(n-a[i]).add(i);
                continue;
            }
            Queue<Integer> in = new LinkedList<>();
            in.add(i);
            temp.put(n-a[i], in);
        }
        for (int i = 0; i < a.length; i++) {
            Queue<Integer> queue = temp.get(a[i]);
            if (null != queue) {
                index2.add(i);
                index1.add(queue.peek());
                if (!queue.isEmpty()) {
                    queue.remove();
                    temp.put(a[i], queue);
                }
            }
        }
        ans.put(1, index1);
        ans.put(2, index2);
        return ans;
    }
    public static void sort(int a[]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j < a.length - i; j++) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        int a[] = {3, 7, 101, 3, 7};
        int n = 10;
        HashMap ans = get2(a, 10);
        ArrayList index1 = (ArrayList) ans.get(1);
        ArrayList index2 = (ArrayList) ans.get(2);
        for (int i = 0; i < index1.size(); i++) {
            System.out.println(index1.get(i) + " " + index2.get(i));
        }

    }
}
