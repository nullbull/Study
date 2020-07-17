package main.java.Runtime.NO1;

public class SecurityGate {
    private int counter = 0;
    public  synchronized void enter(){
       int currentCounter = counter;
       currentCounter++;
        counter = currentCounter;
    }
    public synchronized void eixt(){
        int currentCounter = counter;
        currentCounter--;
        counter = currentCounter;
    }
    public int getCounter(){
        return counter;
    }
}
