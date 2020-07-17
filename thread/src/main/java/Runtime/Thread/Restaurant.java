package main.java.Runtime.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Meal {
    private int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal: " + orderNum;
    }
}
class WaitPerson implements Runnable {
    private Restaurant restaurant;

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (this) {
                    try {
                         while (restaurant.meal == null) {
                             wait();
                         }
                        System.out.println("Get Meal " + restaurant.meal);
                         synchronized (restaurant.chef) {
                             restaurant.meal = null;
                             restaurant.chef.notifyAll();
                         }
                    } catch (InterruptedException e) {
                        System.out.println("ERROR");
                    }

            }
        }
    }
}
class Chef implements Runnable {

    private Restaurant restaurant;
    private int count = 0;
    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
        try {
            synchronized (this) {
                while (restaurant.meal != null)
                    wait();
            }
            if(++count == 10) {
                restaurant.service.shutdownNow();
            }
            System.out.printf("Order up");
            synchronized (restaurant.person) {
                restaurant.meal = new Meal(count);
                restaurant.person.notifyAll();
            }
        }catch (InterruptedException e){
            System.out.println("ERROR");
        }
        }
    }
}
public class Restaurant {
    Meal meal;
    Chef chef;
    WaitPerson person;
     ExecutorService service = Executors.newCachedThreadPool();
    public Restaurant(){
        chef = new Chef(this);
        person = new WaitPerson(this);
        service.execute(chef);
        service.execute(person);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
