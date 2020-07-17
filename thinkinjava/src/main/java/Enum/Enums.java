package main.java.Enum;

import java.util.Random;

public class Enums {
    private static Random rand = new Random(47);
    public static <T extends Enum<T> > T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }
    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}
enum Activity {
    Siting, Lying, Standing, Hopping, Running, Dodging, Jumping, Falling, Flying
}
class RandomeTest {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++)
            System.out.println(Enums.random(Activity.class));
    }
}
