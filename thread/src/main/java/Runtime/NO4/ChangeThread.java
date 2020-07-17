package main.java.Runtime.NO4;

import java.util.Random;

public class ChangeThread extends Thread {
    private final Data data;
    private Random random = new Random();
    public ChangeThread(String name, Data data) {
        super(name);
        this.data = data;
    }
    public void run(){
        try {
            for(int i = 0; true; i++){
                data.change("No. " + i);
                Thread.sleep(random.nextInt(1000));
                data.save();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
