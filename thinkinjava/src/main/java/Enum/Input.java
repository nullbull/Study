package main.java.Enum;

import java.util.Random;

public enum Input {
    Nickel(5),
    Dime(10), Quater(25), Dollar(100), Toothpaste(200), Chips(75), Soda(100), Soap(50),
    Abort_Tramsaction {
        public int amount() {
            throw new RuntimeException("Abort.amount()");
        }
    },
    Stop {
        public int amount() {
            throw new RuntimeException("Shut_Down.amount()");
        }
    };
    int amount() {return value;}
    int value;
    static Random random = new Random(47);
    private Input(int v) {
        this.value = v;
    }
    Input() {}
    public static Input randomeSelection() {
        return values()[random.nextInt(values().length)];
    }
}
