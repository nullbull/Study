package main.java.Enum;

public enum OzWitch {
    WEST("Miss Gulch, aka the Wicked Witch of the West"),
    NORTH("Glina, zzzzzzzzzzzzzzzz"),
    EAST("sb"),
    SOUTH("HandSome");
    private String description;
    private OzWitch(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        for (OzWitch witch : OzWitch.values())
            System.out.println(witch + " : " + witch.getDescription());
    }
}
