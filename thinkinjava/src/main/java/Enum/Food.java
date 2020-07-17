package main.java.Enum;

public interface Food {
    enum Apptizer implements Food{
        SALAD, SOUP, SPRING_ROLLS;
    }
    enum ManinCourse implements Food {
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMOUS, VINDALOO;
    }
    enum Dessert implements Food {
        Tiramisu, Gelato, Black_Forest_Cake,
        Fruit, Creme_Caramel;
    }
    enum Coffee implements Food {
        Black_Coffee, Decaf_Coffee, Espresso,
        Latte, Cappuccino, Tea, Herb_Tea;
    }
}
class TypeOfFood{
    public static void main(String[] args) {
        Food food = Food.Apptizer.SALAD;
        food = Food.ManinCourse.LASAGNE;
    }
}
