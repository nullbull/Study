package main.java.IO;

public class ToyAbstract implements ToyInterface {
    @Override
    public String playBoy(String boy) {
        System.out.println(boy + "is playing abstract toy");
        return boy;
    }
}
