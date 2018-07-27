package Lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class java0726 {
    public java0726() throws IOException {}

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor()) ;
    }
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> before, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : before) {
            if (predicate.test(apple))
                result.add(apple);
        }
        return result;
    }
    interface ApplePredict {
        String accept(Apple apple);
    }
    static class StringPredict implements ApplePredict {


        @Override
        public String accept(Apple apple) {
            StringBuffer sf = new StringBuffer();
            sf.append(apple.getColor()).append("apple  is");
            return apple.getWeight() > 150 ? sf.append("heavy ").toString() : sf.append("light" ).toString();
        }
    }
    public static void  filterApples(List<Apple> before, ApplePredict predicate) {
        for (Apple apple : before)
            System.out.println( predicate.accept(apple) );
    }

    /*
        函数式接口
     */
    @FunctionalInterface
    static interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException ;
    }
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("123.txt"))){
           return br.readLine();
        }
    }
    String hha = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    public static int compute(int a, Function<Integer, Integer> function) {
        int result = function.apply(a);
        return result;
    }
    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(new Apple("red", 113), new Apple("green", 150), new Apple("hh", 200));
        filterApples(appleList, java0726::isGreenApple).forEach(System.out::println);
        appleList.stream().filter((Apple a) -> "green".equals(a.getColor())).forEach(System.out::println);
        filterApples(appleList, new StringPredict());
        appleList.sort(Comparator.comparing(Apple::getWeight)) ;
        appleList.sort((Apple a1, Apple a2) -> a1.getWeight() > a2.getWeight() ? 1 : -1);
        appleList.forEach(System.out::println);
        new Thread(() ->{
            System.out.println("jh");
        }).run();
        BiFunction<String, Integer, Apple> a3 = Apple::new;
        Apple ap3 = a3.apply("zwt", 90);
        System.out.println(ap3);
        Map<String, Integer>  test = new HashMap<>();
        test.put("big", 120);
        test.put("smal", 30);
        System.out.println(java0726.compute(5, x -> x * x));
        System.out.println(java0726.compute(5, x -> x - x));
        System.out.println(java0726.compute(5, x -> x / x));

    }

}
