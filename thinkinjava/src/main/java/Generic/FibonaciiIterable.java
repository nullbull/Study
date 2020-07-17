package Java.Generic;

import java.util.Iterator;

public  class FibonaciiIterable  extends Fibonacii implements Iterable<Integer> {
    private int n = 0;

    public FibonaciiIterable(int n) {
        this.n = n;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return n > 0;
            }

            @Override
            public Integer next() {
                n--;
                System.out.println(FibonaciiIterable.this.next());
                return FibonaciiIterable.this.next();
            }
        };
    }


    public static void main(String[] args) {
        Fibonacii fibonacii = new Fibonacii();
//        for(int i = 0; i< 18; i++)
//            System.out.println(fibonacii.next());
        FibonaciiIterable fibonaciiIterable = new FibonaciiIterable(10);
        for (Integer i : fibonaciiIterable) {
            System.out.println(i);
        }

    }
}