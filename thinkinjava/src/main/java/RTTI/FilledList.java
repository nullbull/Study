package Java.RTTI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class CountedInteger{
    private static long counter;
    private final long id = counter++;
    public String toString(){ return Long.toString(id); }
}
public class FilledList<T> {
    private Class<T> type;
    public FilledList(Class<T> type){ this.type = type; }
    public List<T> create(int n){
        List<T> result = new ArrayList<>();
        try{
            for(int i = 0; i < n; i++)
                result.add(type.newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void main(String[] args){
        FilledList<CountedInteger> fl =
                new FilledList<>(CountedInteger.class);
        Random random = new Random(47);

        System.out.println(fl.create(15));
        for(int i = 0; i < 10; i++){
            System.out.println(random.nextInt(80));
        }

    }
}
