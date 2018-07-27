package Java.Object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ListFeatures {
    public static void main(String[] args){
        List<String> list = Arrays.asList("1", "2", "3", "4");
        String four = new String("4");
        ArrayList<String> arrayList = new ArrayList<>(list);
        ListIterator<String> it = arrayList.listIterator();
        while (it.hasNext()){
            System.out.println(it.next() + ", " + it.nextIndex() + ", " + it.previousIndex());
        }
        System.out.println(list.size());
        System.out.println( arrayList.remove(four));
    }

}
