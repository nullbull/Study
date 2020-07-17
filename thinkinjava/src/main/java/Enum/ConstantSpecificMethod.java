package main.java.Enum;

public enum  ConstantSpecificMethod {
    DATE_TIME {
        @Override
        String getInfo() {
            return "2018-5-4";
        }
    },
    CLASS_PATH {
        @Override
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    };
    abstract String getInfo();

    public static void main(String[] args) {
        for (ConstantSpecificMethod c : values())
            System.out.println(c.getInfo());
    }
}
