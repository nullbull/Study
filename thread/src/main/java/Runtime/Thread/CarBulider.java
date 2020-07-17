package main.java.Runtime.Thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

class Car {
    private int id;
    private  boolean engine = false, driveTrain = false, wheels = false;
    public Car(int id) {
        this.id = id;
    }
    public Car() {
        id = -1;
    }

    public  synchronized int getId() {
        return id;
    }
    public  synchronized void addEngine() {
        engine = true;
    }
    public synchronized void addDriveTrain() {
        driveTrain = true;
    }
    public synchronized void addWheels() {
        wheels = true;
    }

    @Override
    public String toString() {
        return "Car " + id + "[" + " engine: " + engine +
                " drivenTrain " + driveTrain + " wheels " + wheels
                + "]";
    }
}
class  CarQueue extends LinkedBlockingQueue<Car> {}
class ChassisBuilder implements Runnable {

    private CarQueue cars;
    private int counter = 0;
    public ChassisBuilder(CarQueue q) { cars = q;}


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                Car c = new Car(counter++);
                System.out.println("ChassBuilder created " + c);
                cars.put(c);
            }
        }catch (InterruptedException e){
            System.out.println("Interrupted ChassisBuilder");
        }
        System.out.println("ChassisBuilder off");
    }
}
class Assemebler implements Runnable {

    private CarQueue chassisQuque, finishingQueue;
    private Car car;
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;
    public Assemebler(CarQueue cars, CarQueue fq, RobotPool robotPool) {
        chassisQuque = cars;
        finishingQueue = fq;
        this.robotPool = robotPool;
    }
    public Car car() {return car;}

    public CyclicBarrier getBarrier() {
        return barrier;
    }


    @Override
    public void run() {
        try {

            while (!Thread.interrupted()){
             car = chassisQuque.take();
             robotPool.hire(EngineRobot.class, this);
             robotPool.hire(DriveTrianRobot.class, this);
             robotPool.hire(WheelRobot.class, this);
             barrier.await();
             finishingQueue.put(car);
            }


        }catch (InterruptedException e) {
            System.out.println("Exiting Assembler via interrupt");

        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("Assembler off");
    }
}
class Reporter implements Runnable{
    private CarQueue cars;
    public Reporter(CarQueue cars) {this.cars = cars ;}

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(cars.take());
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting Reporter via interrupt");
        }
        System.out.println("Reporter off");
    }
}
abstract class Robot implements Runnable {
    private RobotPool pool;
    protected Assemebler assemebler;
    public Robot(RobotPool robotPool) { pool = robotPool;}
    public Robot assignAssembler(Assemebler assemebler) {
        this.assemebler = assemebler;
        return this;
    }
    private boolean engine = false;
    public synchronized void engine() {
        engine = true;
        notifyAll();
    }
    abstract protected void performService();
    public void run() {
        try {
            powerDown();
            while (!Thread.interrupted()) {
                performService();
                assemebler.getBarrier().await();
                powerDown();
            }
        }catch (InterruptedException e) {
            System.out.println("Exiting " + this + " via interrupt");
        }catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this + " Off");
    }
    private synchronized void powerDown() throws InterruptedException {
        engine = false;
        assemebler = null;
        pool.release(this);
        while (engine == false)
            wait();
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
class EngineRobot extends Robot {

    public EngineRobot(RobotPool pool) { super(pool);}
    @Override
    protected void performService() {
        System.out.println(this + " installing engine");
        assemebler.car().addEngine();
    }
}
class DriveTrianRobot extends Robot {

    public DriveTrianRobot(RobotPool pool) { super(pool);}
    @Override
    protected void performService() {
        System.out.println(this + " installing DriveTrain");
        assemebler.car().addDriveTrain();
    }
}
class WheelRobot extends Robot {

    public WheelRobot(RobotPool pool) { super(pool);}
    @Override
    protected void performService() {
        System.out.println(this + " installing Wheels");
        assemebler.car().addWheels();
    }
}
class RobotPool{
    private Set<Robot> pool = new HashSet<>();
    public synchronized void add(Robot r) {
        pool.add(r);
        notifyAll();
    }
    public synchronized void hire(Class< ? extends Robot> robotType, Assemebler d) throws InterruptedException {
        for (Robot r : pool)
            if (r.getClass().equals(robotType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engine();
                return;
            }
            wait();
        hire(robotType, d);
    }
    public synchronized void release(Robot r) { add(r);}
}
public class CarBulider {
    public static void main(String[] args) throws InterruptedException {
        CarQueue chassisQueue = new CarQueue(),
                finishingQueue = new CarQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        RobotPool pool = new RobotPool();
        exec.execute(new EngineRobot(pool));
        exec.execute(new DriveTrianRobot(pool));
        exec.execute(new WheelRobot(pool));
        exec.execute(
                new Assemebler(chassisQueue, finishingQueue, pool)
        );
        exec.execute(new Reporter(finishingQueue));
        exec.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
}
