package main.java.Runtime.Thread;
class DualSynch {
    private Object syncObject = new Object();
    public synchronized void f() {
        for(int i = 0; i < 500; i++) {
            System.out.println("f() " + i);
            Thread.yield();
        }
    }
    public void g() {
        synchronized (syncObject) {
            for(int i = 0; i < 500; i++) {
                System.out.println("g() " + i);
                Thread.yield();
            }
        }
    }
}
public class SyncObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread() {
            public void run() {
                ds.f();
            }
        }.start();
        ds.g();
    }
}
