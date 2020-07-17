package main.java.Enum;

public enum SecurityCategory {

    STOCK (Security.Stock.class), BOND(Security.Bond.class);
    interface Security{
        enum Stock implements Security{ Short, Long, Margin }
        enum Bond implements Security { Municipal, Junk}
    }
    Security[] values;
    private SecurityCategory(Class<? extends Security> type) {
        values = type.getEnumConstants();
    }
    public Security randomSelection(){
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            SecurityCategory category = Enums.random(SecurityCategory.class);
            System.out.println(category.randomSelection());
        }
    }
}
