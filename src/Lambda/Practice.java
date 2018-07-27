package Lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Practice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );



        List<Transaction> a1 = transactions.stream().filter(t -> t.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        a1.forEach(System.out::println);
        List<String> a2 = transactions.stream().map(Transaction::getTrader).distinct().map(Trader::getCity).distinct().collect(Collectors.toList());
        System.out.println(a2);
        List<Trader> a3 = transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(Transaction::getTrader).sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        System.out.println(a3);
        List<String> a4 = transactions.stream().map(Transaction::getTrader).map(Trader::getName).distinct().sorted(String::compareToIgnoreCase).collect(Collectors.toList());
        String a5 = a4.stream().reduce("", (a, b) -> a + b);
        System.out.println(a5);
        System.out.println(transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan")));
        System.out.println(transactions.stream().filter(t -> t.getTrader().getCity().equals("Milan")).map(transaction -> transaction.getValue()).reduce(0, (a, b) -> a + b));
        System.out.println(transactions.stream().map(Transaction::getValue).reduce(Integer::max).get());
        System.out.println(transactions.stream().sorted(Comparator.comparing(Transaction::getValue)).limit(1).collect(Collectors.toList()));
        System.out.println(transactions.stream().min(Comparator.comparing(Transaction::getValue)));
    }
}

