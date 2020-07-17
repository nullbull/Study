package main.java.Runtime.NO1;

public class CrackerThread extends Thread {
    private final SecurityGate sg;
    CrackerThread(SecurityGate securityGate){
        this.sg = securityGate;
    }
    public void run(){
        sg.enter();
        sg.eixt();
    }
    public static void main(String[] args){
        for(int trial = 0; true; trial++){
            SecurityGate gate = new SecurityGate();
            CrackerThread[] t = new CrackerThread[5];
            for (int i = 0; i < t.length; i++) {
                t[i] = new CrackerThread(gate);
                t[i].start();
            }
            for(int i = 0; i < t.length; i++) {
                try {
                    t[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if( gate.getCounter() == 0)
                System.out.println(".");
            else
                System.out.println("Trial " + trial );
        }
    }
}
