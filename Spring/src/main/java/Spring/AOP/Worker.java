package main.java.Spring.AOP;

public class Worker {
    public void take(){
        System.out.println("观众已全部交出手机");
    }

    public void sendMsg(String name){
        System.out.println(name + "表演即将开始，请各位观众交出手机");
    }

    public void broadcast(String performer, String title){
        System.out.println(performer + "演奏完毕，刚才演奏的曲子叫：" + title);
    }
}
