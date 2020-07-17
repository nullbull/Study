package main.java.IO;

public class Toy extends ToyAbstract {
    private String name;
    private  String color;
    protected int size;
    public Toy(){
        System.out.println("初始化");
        setName("mytoy");
        color = "red";
        size = 7;
    }
    public Toy(String toy, String color, int size){
        this.color = color;
        setName(toy);
        this.size = size;
    }
    @Override
    public String playBoy(String boy) {
        String msg = buildMsg(boy);
        System.out.println(msg);
        return msg;

    }
    private String buildMsg(String name){
        String msg = name + "play" + this.getName();
        return msg;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static int getPrice() {
        return price;
    }

    public static final int price = 10;

}
