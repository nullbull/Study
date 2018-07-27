package Runtime.Thread;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;

class Customer {
    private final int serviceTime;

    public Customer(int serviceTime) {this.serviceTime = serviceTime;}

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "[" + serviceTime + "]";
    }
}
class CustomerLine extends ArrayBlockingQueue<Customer> {

    public CustomerLine(int capacity) {
        super(capacity);
    }
    public String toString() {
        if(this.size() == 0)
            return "[Empty]";
        StringBuilder result = new StringBuilder();
        for (Customer customer : this)
            result.append(customer);
        return result.toString();
    }
}
class CustomerGenerator implements Runnable {

    private CustomerLine customers;
    private static Random random = new Random(47);
    public CustomerGenerator(CustomerLine c) {
        customers = c;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                customers.put(new Customer(random.nextInt(100)));
            }
        }catch (InterruptedException e) {
            System.out.println("CustomerGenerator terminating");
        }
        System.out.println("CustomerGenerator terminating");
    }
}
class Teller implements Runnable, Comparable<Teller> {

    private static int counter = 0;
    private final int id = counter++;
    private int customerServed = 0;
    private CustomerLine customers;
    private boolean servingCustomerLine = true;
    public Teller(CustomerLine cq) { customers = cq;}



    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Customer customer = customers.take();
                TimeUnit.SECONDS.sleep(customer.getServiceTime());
                synchronized (this) {
                    customerServed++;
                    while (!servingCustomerLine)
                        wait();
                }
            }
        }catch (InterruptedException e) {
            System.out.println(this + "Interrupted");
        }
        System.out.println(this + " Terminating");
    }
    public synchronized void doSomethingElse() {
        customerServed = 0;
        servingCustomerLine = true;
        notifyAll();
    }
    public synchronized void serveCustomerLine() {
        assert !servingCustomerLine : "already serving :" + this;
        servingCustomerLine = true;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Teller " + id + " ";
    }
    public String shortString() { return "T" + id;}
    public synchronized int compareTo(Teller teller) {
        return customerServed < teller.customerServed ? -1 :
                (customerServed > teller.customerServed ? 1 : 0);
    }
}

class TellerManager implements Runnable {
    private ExecutorService exec;
    private CustomerLine customers;
    private PriorityBlockingQueue<Teller> workingTellers = new PriorityBlockingQueue<>();
    private Queue<Teller> tellersDoingOtherThings = new LinkedList<>();
    private int adjustmentPeriod;
    private static  Random random = new Random(47);
    public TellerManager(ExecutorService service,CustomerLine line, int adjustmentPeriod) {
        exec = service;
        customers = line;
        this.adjustmentPeriod = adjustmentPeriod;
        Teller teller = new Teller(line);
        exec.execute(teller);
        workingTellers.add(teller);
    }
    public void adjustTellerNumber() {
        if (customers.size() / workingTellers.size() > 2) {
            if(tellersDoingOtherThings.size() > 0) {
                Teller teller = tellersDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return;
            }
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
            return;
        }
            if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2){
                ressignOneTeller();
            }
            if(customers.size() == 0)
                while(workingTellers.size() > 1)
                    ressignOneTeller();
    }
    private void ressignOneTeller() {
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }
    @Override
    public void run() {
        try{
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(customers + " { ");
                for (Teller teller : workingTellers)
                    System.out.print(teller.shortString() + " ");
                System.out.println(" }");
            }
        } catch (InterruptedException e) {
            System.out.println("TellerManager interrupted");
        }
        System.out.println(this + " terminating");
    }

    @Override
    public String toString() {
        return "TellerManager";
    }
}
public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws InterruptedException, IOException {
        ExecutorService exec = Executors.newCachedThreadPool();
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));

                exec.execute(new TellerManager(
                exec, customers, ADJUSTMENT_PERIOD
        ));

       if(args.length > 0)
           TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else
        {
            System.out.println("Press to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
