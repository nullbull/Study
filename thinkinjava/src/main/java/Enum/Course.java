package Enum;

public enum Course {
    Appetizer(Food.Apptizer.class),
    MainCourse(Food.ManinCourse.class),
    Dessert(Food.Dessert.class),
    Coffee(Food.Coffee.class);
    private Food[] values;
    private Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }
    public Food randomSelection() {
        return Enums.random(values);
    }
}
    class meal{
        public static void main(String[] args) {
            for (int i = 0; i < 5; i++) {
                for (Course course : Course.values()) {
                    Food  food = course.randomSelection();
                    System.out.println(food);
                }
                System.out.println("=======");
            }
        }
    }

