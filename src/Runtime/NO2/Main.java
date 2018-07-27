package Runtime.NO2;

public class Main {
    public static void main(String[] args) {
        Person alice = new Person("Alice", "Alaska");
        Person zz = new Person("zz", "niutun");
        new PrintPersonThread(alice).start();
        new PrintPersonThread(alice).start();
        new PrintPersonThread(zz).start();
    }
}
