package main.java.Runtime.NO1;

public class Bank {
    private int money;
    private String name;
    public Bank(String name, int money){
        this.money = money;
        this.name = name;
    }
    public synchronized void deposit(int m){
        money += m;
    }
    public synchronized boolean withdeaw(int m){
        if(money >= m){
            money -= m;
            return true;
        }else
            return false;
    }

    public String getName() {
        return name;
    }
}
