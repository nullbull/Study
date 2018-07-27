package Java.Object;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetOfInteger {
    public static void main(String[] args){
        Random random = new Random(47);
        Set<Integer> integerSet = new HashSet<Integer>();
        for (int i = 0; i < 10000; i++){
            integerSet.add(random.nextInt(30));
        }
        System.out.println(integerSet);


    }
}
