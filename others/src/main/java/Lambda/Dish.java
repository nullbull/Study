package Lambda;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;

public class Dish {
    public enum CaloricLevel {DIEF, NORMAl,FAT}

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public enum Type {MEAT, FISH, OTHER}

    @Override
    public String toString() {
        return name;
    }

    public static final List<Dish> menu =
            Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                    new Dish("beef", false, 700, Dish.Type.MEAT),
                    new Dish("chicken", false, 400, Dish.Type.MEAT),
                    new Dish("french fries", true, 530, Dish.Type.OTHER),
                    new Dish("rice", true, 350, Dish.Type.OTHER),
                    new Dish("season fruit", true, 120, Dish.Type.OTHER),
                    new Dish("pizza", true, 550, Dish.Type.OTHER),
                    new Dish("prawns", false, 400, Dish.Type.FISH),
                    new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        List<String> hh = menu.stream().filter(d -> {
            System.out.println("filtering " + d.getName());
            return d.getCalories() > 300;
        }).map(d -> {
                    System.out.println("mapping " + d.getName());
                    return d.getName();
                }).limit(3).collect(Collectors.toList());
        hh.forEach(System.out::println);
        List<Dish> ve = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        System.out.println(ve);
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(x -> x % 2 == 0).distinct().forEach(System.out::println);
        List<Integer> dis = menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
        String [] word = {"Hello", "world"};
        List<String[]> zz = Arrays.stream(word).map(w -> w.split("")).distinct().collect(Collectors.toList());
        List<String> zz2 = Arrays.stream(word).map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        zz2.forEach(System.out::println);
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("xxxxxxx");
        }
        int sum = numbers.stream().reduce(0, (a , b) -> a + b);
         sum = numbers.stream().reduce(1, (a , b) -> a * b);
        System.out.println(sum);

        System.out.println(numbers.stream().reduce(Integer::sum));
        System.out.println(numbers.stream().reduce(Integer::max));
        System.out.println(numbers.stream().reduce(Integer::min).get());


        System.out.println(menu.stream().map(Dish::getCalories).reduce(0, Integer::sum));
        System.out.println(menu.stream().mapToInt(Dish::getCalories).sum());
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> zzz = intStream.boxed();


        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());
        int [][] gougu = new int[100][];
        Stream<int[]> py = IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        Stream<int[]> pt2 = IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})).filter(t -> t[2] % 1 == 0);
        pt2.limit(5).forEach(System.out::println);
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20).forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().reduce(BinaryOperator.maxBy(dishComparator));
        menu.stream().min(dishComparator);
        System.out.println(mostCalorieDish.get());
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));



        System.out.println(totalCalories);
        //求平均数，最大最小值，和
        System.out.println(menu.stream().collect(summarizingInt(Dish::getCalories)));
        System.out.println(menu.stream().map(Dish::getName).collect(Collectors.joining(",", "(", ")")));
        Map<Dish.Type, List<Dish>> dddd = menu.stream().collect(groupingBy(Dish::getType));

        Map<Dish.Type,Map<CaloricLevel, List<Dish>>> disLevel = menu.stream().collect(groupingBy(Dish::getType, groupingBy(
                dish -> {
                    if (dish.getCalories() < 400) return CaloricLevel.DIEF;
                    else if(dish.getCalories()<=700) return CaloricLevel.NORMAl;
                    else return CaloricLevel.FAT;
                }
        )));
        System.out.println(disLevel);
    }
}

