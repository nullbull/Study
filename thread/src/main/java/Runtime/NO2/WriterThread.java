package main.java.Runtime.NO2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class readrThread extends Thread {
    private List<Integer> list;
    public readrThread(List list){
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
                synchronized (list) {
                    for (int n : list){
                        System.out.println(n);
                    }
                }
        }
    }
}

public class WriterThread  extends Thread{
    private final List<Integer> list;
    public WriterThread(List list){
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
            list.add(i);
            list.remove(0);
            //System.out.println(list.get(i));
        }
    }
    public static void main(String[] args){
       final List<Integer> list = Collections.synchronizedList( new ArrayList<>() );
        WriterThread writerThread = new WriterThread(list);
        readrThread readrThread = new readrThread(list);
        writerThread.start();
        readrThread.start();
    }
}
