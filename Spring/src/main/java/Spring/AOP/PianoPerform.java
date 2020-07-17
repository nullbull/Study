package main.java.Spring.AOP;

public class PianoPerform implements Performance {
    public PianoPerform(){
        System.out.println("有请钢琴表演");
    }

    @Override
    public void perform() {
        System.out.println("钢琴表演开始");
    }

    @Override
    public void finishPerform(String performer, String title) {
        System.out.println(performer + "演奏钢琴曲：" + title);
    }
}
