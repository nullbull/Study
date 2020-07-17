package main.java.Enum;

import Java.Generic.Generator;

import java.util.Random;

enum CartoonCharacter implements Generator<CartoonCharacter> {
    slappu, spanky, punchy, silly, bouncy, nutty, bob;
    private Random random = new Random(47);
    @Override
    public CartoonCharacter next() {
        return values()[random.nextInt(values().length)];
    }
}
public class EnumImplementation {
    public static <T> void printNext(Generator<T> rg) {
        System.out.print(rg.next() + ",");
    }

    public static void main(String[] args) {
        CartoonCharacter cc = CartoonCharacter.bob;
        for (int i = 0; i < 10; i++)
            printNext(cc);
    }
}
