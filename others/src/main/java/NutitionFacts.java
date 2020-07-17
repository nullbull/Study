package main.java;

public class NutitionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;
    public static class Builder {
        private final int servingSize;
        private final int servings;
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;
        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }
        public Builder calories(int val) {
            calories = val; return this;
        }
        public Builder fat(int val) {
            fat = val; return this;
        }
        public Builder carbohydrate(int val) {
            carbohydrate = val; return this;
        }
        public Builder sodium(int val) {
            sodium = val; return this;
        }
        public NutitionFacts build() {
            return new NutitionFacts(this);
        }
    }


    private NutitionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        sodium = builder.sodium;
        fat = builder.fat;
        calories = builder.calories;
        carbohydrate = builder.carbohydrate;
    }

    @Override
    public String toString() {
        return "NutitionFacts{" +
                "servingSize=" + servingSize +
                ", servings=" + servings +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", carbohydrate=" + carbohydrate +
                '}';
    }

    public static void main(String[] args) {
        NutitionFacts nutitionFacts = new Builder(10, 5).calories(10).fat(20).carbohydrate(200).fat(200).sodium(90).build();
        System.out.println(nutitionFacts);
    }

}
