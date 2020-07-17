package main.java.Spring.soundSystem;

import org.springframework.stereotype.Component;

@Component
public class CDPlayer implements MediaPlayer {
    private  CompactDisc cd;

    public CDPlayer(CompactDisc cd){
        this.cd = cd;
    }
    public void play(){
        cd.play();
    }
    public void setCd(CompactDisc cd){
        this.cd = cd;
    }
}
