package main.java.Object;

import java.util.*;

public class AddingGroups {
    public static void main(String[] args){
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] moreInts = {6, 7, 8, 9, 10};
        collection.addAll(Arrays.asList(moreInts));
        Collections.addAll(collection, 11, 12, 13, 14, 15);
        Collections.addAll(collection, moreInts);
        List<Integer> list = Arrays.asList(16, 17, 18, 19);
        int[] x = new int[10];

        list.set(1, 99);
        List list1 = Arrays.asList("11", "22");
        for(Object object : collection){
            System.out.println(object);
        }
        for (int i : list){
            System.out.println(i);
        }
    }
}
