package Runtime.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class testSynch{
  private  volatile int i;
  public synchronized void increaseI(){
      i++;
  }
  public synchronized int getI() {
      return i;
  }
}
class TestTread implements Runnable{

    private testSynch testSynch1;
    private testSynch testSynch2;

    public TestTread(testSynch testSynch1, testSynch testSynch2) {
        this.testSynch2 = testSynch2;
        this.testSynch1 = testSynch1;
    }

    @Override
    public void run() {
        synchronized (testSynch1){

        }
        for(int i = 0; i < 10; i++) {
            testSynch1.increaseI();
            testSynch2.increaseI();
        }
        System.out.println(testSynch1.getI());
        System.out.println(testSynch2.getI());
    }
}
public class test {
    public static void main(String[] args) {
        testSynch testSynch1 =  new testSynch();
        testSynch testSynch2 =  new testSynch();
        ExecutorService service = Executors.newCachedThreadPool();
        TestTread testTread = new TestTread(testSynch1, testSynch2);
        service.execute(testTread);
    }
}
