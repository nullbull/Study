package Java.Collection;

import Java.Generic.Generator;
import javafx.util.Pair;

import java.util.Iterator;
import java.util.LinkedHashMap;

class MapData<K, V> extends LinkedHashMap<K, V>  {
    public MapData(Generator<Pair<K, V>> gen, int size) {
        for (int i = 0; i < size; i++) {
            Pair<K, V> pair = gen.next();
            put(pair.getKey(), pair.getValue());
        }
    }
}
class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer> {

    private int size = 9;
    private int number = 1;
    private char letter = 'A';
    @Override
    public Pair<Integer, String> next() {
        return new Pair<>(number++, "" + letter++);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return number < size;
            }

            @Override
            public Integer next() {
                return number++;
            }
        };
    }
}
public class MapDataTest {
    public static void main(String[] args) {
        System.out.println(new MapData<Integer, String>(new Letters(), 11));
    }
}
