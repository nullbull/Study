package Runtime.NO3;

public class Main {
    public static void main(String[] args){
        RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "Alice", 3141592l).start();
        new ServerThread(queue, "Bobby", 3141592l).start();
    }
}
