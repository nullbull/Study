package Java.Generic;

import java.util.ArrayList;
import java.util.Collection;

public class Generators {
    public static <T>Collection<T> fill(Collection<T> coll, Generator<T> gen, int n){
        for(int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static void main(String[] args) {
        Collection<Coffee>coll = fill(new ArrayList<Coffee>(), new CoffeeGenerator(), 5);
        for(Coffee c : coll)
            System.out.println(c);
    }
}
