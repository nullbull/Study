package main.java.Runtime.NO5;

public class Main {
    public static void main(String[] args) {
        Table table = new Table(3);
        new MakeThread("MakeThread--1", table, 31415).start();
        new MakeThread("MakeThread--2", table, 98688).start();
        new MakeThread("MakeThread--3", table, 23131).start();
        new EaterThread("EaterThread--1", table, 31415).start();
        new EaterThread("EaterThread--2", table, 15543).start();
        new EaterThread("EaterThread--3", table, 94324).start();
    }
}
