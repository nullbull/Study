package main.java.Generic;

public class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}
    class Latte extends Coffee{}
    class Mocha extends Coffee{}
    class Cappuccino extends Coffee{}
    class Americano extends Coffee{}
    class Breve extends Coffee{}
//    class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee>{
//        private Class[] types = {
//                Latte.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class
//        };
//        private static Random random = new Random(47);
//        public CoffeeGenerator(){}
//        private int size = 0;
//        public CoffeeGenerator(int size) { this.size = size;}
//
//        @Override
//        public Coffee next() {
//            try {
//                return (Coffee) types[random.nextInt(types.length)].newInstance();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            }
//            return new Latte();
//        }
//
//        @Override
//        public Iterator<Coffee> iterator() {
//            return null;
//        }
//
//        class CoffeeIterator implements Iterable<Coffee> {
//            int count = size;
//            public boolean hasNext() {return count > 0;}
//            public Coffee next() {
//                count--;
//                return CoffeeGenerator.this.next();
//            }
//            @Override
//            public Iterator<Coffee> iterator() {
//                return new CoffeeIterator();
//            }
//        }
//    }
