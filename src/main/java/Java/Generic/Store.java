package Java.Generic;

import java.util.ArrayList;
import java.util.Random;

class Product {
    private final int id;
    private String description;
    private double price;
    public Product(int id, String des, double price) {
        this.description = des;
        this.id = id;
        this.price = price;
    }

    @Override
    public String toString() {
        return  "id " + id + " description " + description + " price " + price;
    }
    public static Generator<Product> generator = new Generator<Product>() {
        private Random random = new Random(47);

        public Product next() {
            return new Product(random.nextInt(1000), "Test", Math.round(random.nextDouble() * 1000.0) + 0.99);
        }
    };
    public void change(double price) {
        this.price += price;
    }
}
class Shelf extends ArrayList<Product> {
    public Shelf(int n) {
       Generators.fill(this, Product.generator, n);
    }
}
class Aisle extends ArrayList<Shelf> {
    public Aisle(int nShelf, int nProduct) {
        for(int i = 0; i < nShelf; i++)
            add(new Shelf(nProduct));
    }
}
public class Store extends ArrayList<Aisle>{
        public Store(int n, int nShelves, int nProducts) {
            for(int i = 0; i < n; i++)
                add(new Aisle(nShelves, nProducts));
        }

    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        for(Aisle a : this) {
            for(Shelf s : a)
                for(Product p : s) {
                string.append(p);
                string.append("\n");
                }
        }
                return string.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Store(14, 5, 10));
    }
}
