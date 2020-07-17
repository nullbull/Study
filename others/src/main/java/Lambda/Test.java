package Lambda;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Test {
    static Predicate<Integer> atLeast5 = x -> x > 5;

    static class IntgerComp implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 > o2 ? 1 : -1;
        }
    }
    public static int countLowercaseLetters(String string) {
        return (int) string.chars().filter(Character::isLowerCase).count();
    }

    public static void main(String[] args) {



        String test = "aaaBBASDasdsadasdasdad";
        System.out.println(countLowercaseLetters(test));
        Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 3, 2, 1));
        List<Integer> sameOrder = numbers.stream().sorted().collect(Collectors.toList());
        sameOrder.stream().forEach(integer -> System.out.println(integer));
        //sameOrder = sameOrder.stream().map(x -> x + 1).collect(Collectors.toList());
        //System.out.println(sameOrder);
        sameOrder = sameOrder.stream().filter(x -> x > 2).collect(Collectors.toList());
        sameOrder.stream().forEach(System.out::println);
        sameOrder.forEach(x -> System.out.println( x));
//        sameOrder.forEach( System.out::println);
        List<String> track = Arrays.asList("111", "2213", "zz");
        List<Artist> artists = Arrays.asList(new Artist("123", "zz"), new Artist("zz", "123"), new Artist("123", "beji"));
        Map<String, List<Artist>> hh = artists.stream().collect(groupingBy(a -> a.getName()));
        String result = artists.stream().map(Artist::getName).collect(Collectors.joining(",", "[", "]"));
        System.out.println(result);
        //转换成集合
        List<String> collected = Stream.of("a", "b", "c").collect(toList());
        //转换大写
        List<String> collected1 = Stream.of("A", "b", "d").map(s -> s.toUpperCase()).collect(toList());
        List<String> beginningWithNumbers = Stream.of("a", "1abc", "abc1").filter(value -> isDigit(value.charAt(0))).collect(toList());
//        System.out.println(collected1);
        //    System.out.println(beginningWithNumbers);
        //List<Integer> together = Stream.of(asList(1, 2), asList(3, 4)).flatMap(numbers -> numbers.stream()).collect(toList());    }
        List<String> teamIndia = Arrays.asList("Virat", "Dhoni", "Jadeja");
        List<String> teamAustralia = Arrays.asList("Warner", "Watson", "Smith");
        List<String> teamEngland = Arrays.asList("Alex", "Bell", "Broad");
        List<String> teamNewZeland = Arrays.asList("Kane", "Nathan", "Vettori");
        List<String> teamSouthAfrica = Arrays.asList("AB", "Amla", "Faf");
        List<String> teamWestIndies = Arrays.asList("Sammy", "Gayle", "Narine");
        List<String> teamSriLanka = Arrays.asList("Mahela", "Sanga", "Dilshan");
        List<String> teamPakistan = Arrays.asList("Misbah", "Afridi", "Shehzad");

        List<List<String>> playersInWorldCup2016 = new ArrayList<>();
        playersInWorldCup2016.add(teamIndia);
        playersInWorldCup2016.add(teamAustralia);
        playersInWorldCup2016.add(teamEngland);
        playersInWorldCup2016.add(teamNewZeland);
        playersInWorldCup2016.add(teamSouthAfrica);
        playersInWorldCup2016.add(teamWestIndies);
        playersInWorldCup2016.add(teamSriLanka);
        playersInWorldCup2016.add(teamPakistan);

        // Let's print all players before Java 8
        List<String> listOfAllPlayers = new ArrayList<>();

        for (List<String> team : playersInWorldCup2016) {
            for (String name : team) {
                listOfAllPlayers.add(name);
            }
        }

        System.out.println("Players playing in world cup 2016");
        System.out.println(listOfAllPlayers);


        // Now let's do this in Java 8 using FlatMap
        List<String> flatMapList = playersInWorldCup2016.stream()
                .flatMap(pList -> pList.stream())
                .collect(Collectors.toList());

        System.out.println("List of all Players using Java 8");
        System.out.println(flatMapList);
        //求最大值最小值
        List<Track> trackList = Arrays.asList(new Track("zhangxueyou", 4000)
                , new Track("chenyixun", 2000), new Track("July", 1800));
        Track max = trackList.stream().min(Comparator.comparing(t -> t.getNumber())).get();
        System.out.println(max);
        //累加
        int count = Stream.of(1, 2, 4).reduce(0, (acc, element) -> acc + element);
        System.out.println(count);
        String te0st = "jadasdasdaJJJAD";
        System.out.println(test.chars());


        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        double resultDouble = list.stream().collect(Collectors.averagingDouble(d -> d * 2));
        //返回集合的   大小
        Long resu9lt = list.stream().collect(Collectors.counting());
        List<String> string = Arrays.asList("A", "B", "C", "D");
        String re = string.stream().collect(Collectors.joining(","));
        System.out.println(re);
        List<Integer> maxInt = Arrays.asList(30, 10, 20, 35);
        maxInt.stream().collect(Collectors.maxBy(new IntgerComp())).ifPresent(integer -> System.out.println(integer));
        System.out.println(maxInt.stream().collect(Collectors.summingInt(integer -> integer)));

        List<User> userList = Arrays.asList(new User("zwt", 19), new User("justinniu", 22), new User("wyy", 29));
        List<User> after = userList.stream().filter(user -> user.name.length() == 3).collect(toList());
        System.out.println(after);
        userList.stream().forEach(System.out::print);
        userList.sort((user1, user2) -> (user1.age - user2.age));
        userList.forEach(user -> System.out.println(user));
        Predicate<User> filter = user -> user.name.length() > 3;
        List<User> tryAgain = userList.stream().filter(filter).collect(toList());
        System.out.println(tryAgain);
        List<String> userName = userList.stream().map(user -> user.getName()).collect(toList());
        List<User> userList1 = userList.stream().map(user -> new User(user.getName() + "handsome", user.getAge() + 2)).collect(toList());
        System.out.println(userList1);
        userList.add(new User("wyz", 44));

    }
}

