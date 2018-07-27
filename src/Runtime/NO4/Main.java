package Runtime.NO4;

public class Main {
    public static void main(String[] args) {
        Data data = new Data("data.txt", "(empty)");
        SaveThread saveThread = new SaveThread("Save", data);
        ChangeThread changeThread = new ChangeThread("Change", data);
        saveThread.start();
        changeThread.start();
    }
}
