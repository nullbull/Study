package Enum;

import Java.Generic.Generator;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Iterator;

import static Enum.Input.*;

enum Category {
    Money(Nickel, Dime, Quater, Dollar), Item_Selection(Toothpaste, Chips, Soap, Soda),
    Quit_Transaction(Abort_Tramsaction),  Shut_Down(Stop);
    private Input[] value;
    Category(Input... types) {this.value = types;}
    private static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(Input.class);
    static {
        for (Category c : Category.class.getEnumConstants()) {
            for (Input input : c.value) {
                categories.put(input, c);
            }
        }
    }
    public static Category categorize(Input input) {
        return categories.get(input);
    }
}
public class VendingMachine {
    private static int amount = 0;
    private static Input selection = null;
    private static State state = State.Resting;
    enum StateDuration{TRANSIENT}
    enum State {
        Resting {
            void next (Input input) {
                switch (Category.categorize(input)){
                    case Money:
                        amount += input.amount();
                        state = Adding_Money;
                        break;
                    case Shut_Down:
                        state = Terminal;
                        default:
                }
            }
        },
        Adding_Money {
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case Money:
                        amount += input.amount();
                        break;
                    case Item_Selection:
                        selection = input;
                        if(amount < selection.amount())
                            System.out.println("Insufficient money for " + selection);
                        else state = Dispensing;
                        break;
                    case Quit_Transaction:
                        state = Giving_Change;
                        break;
                    case Shut_Down:
                        state = Terminal;
                        default:
                }
            }
        },
        Dispensing(StateDuration.TRANSIENT) {
            void next() {
                System.out.println("here is your " + selection);
                amount -= selection.amount();
                state = Giving_Change;
            }
        },
        Giving_Change{
            void next() {
                if(amount > 0) {
                    System.out.println("Your change: " + amount);
                    amount = 0;
                }
                state = Resting;
            }
        },
        Terminal {
            void output() {
                System.out.println("Halted");
            }
        };
        private boolean isTransient = false;
        State() {}
        State(StateDuration trans) { isTransient = true;}
        void next(Input input) {
            throw new RuntimeException("Only Call " + "");
        }
        void next() {
            throw new RuntimeException("zzzzzzzzzzzzzzz");
        }
        void output() {
            System.out.println(amount);
        }
    }
    static void run(Generator<Input> gen) {
        while (state != State.Terminal) {
            state.next(gen.next());
            while (state.isTransient)
                state.next();
            state.output();
        }
    }
    static class RandomInputGenerator implements Generator<Input> {

        @Override
        public Input next() {
            return Input.randomeSelection();
        }
    }
    public static void main(String[] args) throws IOException {
        Generator<Input> generator = new RandomInputGenerator();
        if (args.length == 1)
            generator = new FileInputGenerator(args[0]);
        run(generator);
    }

}
class FileInputGenerator implements Generator<Input> {
    private Iterator<String> input;
    public FileInputGenerator(String filename) throws IOException {
        input = new TextFile(filename, ";").iterator();
    }
    @Override
    public Input next() {
        if(!input.hasNext())
            return null;
        return Enum.valueOf(Input.class, input.next().trim());
    }
}
