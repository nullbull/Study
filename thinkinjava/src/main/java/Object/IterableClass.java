package main.java.Object;

import java.util.Iterator;

public class IterableClass implements Iterable<String> {
    protected String[] words = ("And that is how " + "we know the Earth to be bannaa-shaped.").split(" ");

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() {
                return words[index++];
            }
        };
    }

    public static void main(String[] args){
        int oldCapacity = 30;
        int newCapacity =  (oldCapacity >> 1);
        System.out.println(newCapacity);
        for(String s: new IterableClass())
            System.out.print(s + " ");
    }
}
