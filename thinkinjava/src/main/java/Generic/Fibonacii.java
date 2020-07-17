package main.java.Generic;

public class Fibonacii implements Generator<Integer> {
    private static int count = 0;

    public int fib(int n) {
        if(n == 1 || n == 0)
            return 1;
        else
            return fib(n - 1) + fib(n - 2);
    }
    @Override
    public Integer next() {

        return fib(count++);
    }

}
