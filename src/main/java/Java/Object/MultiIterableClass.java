package Java.Object;

import java.util.*;

public class MultiIterableClass extends IterableClass {
    protected String[] words = ("And that is how " + "we know the Earth to be bannaa-shaped.").split(" ");

    public Iterable<String> reversed(){

        return new Iterable<String> (){
            public Iterator<String> iterator(){
                return  new Iterator<String>() {
                    int index = words.length - 1;
                    @Override
                    public boolean hasNext() {
                        return index > -1;
                    }

                    @Override
                    public String next() {
                        return words[index--];
                    }
                };

            };
        };
    }
    public Iterable<String> randomized() {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                List<String> shuffled =
                        new ArrayList<>(Arrays.asList(words));
                Collections.shuffle(shuffled, new Random(47));
                return shuffled.iterator();
            };
        };
    }
    public static void main(String[] args){
        MultiIterableClass mic = new MultiIterableClass();
        for(String s : mic.reversed())
            System.out.print(s + " ");
        System.out.println();
        for(String s : mic.randomized())
            System.out.print(s + " ");
    }
}
