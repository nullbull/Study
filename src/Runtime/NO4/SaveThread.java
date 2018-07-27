package Runtime.NO4;

public class SaveThread  extends  Thread{
    private final Data data;

    public SaveThread(String name, Data data){
        super(name);
        this.data = data;
    }
    public void run(){
        try {
            while (true){

            data.save();
            Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
