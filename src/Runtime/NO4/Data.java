package Runtime.NO4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Data {
    private final String fileName;
    private String content;
    private boolean changed;
    public Data(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }
    public synchronized void change(String content){
        this.changed = true;
        this.content = content;
    }
    public synchronized void save() throws Exception {
        if(!changed) return;
        doSave();
        changed = false;

    }
    public void doSave() throws IOException{
        System.out.println(Thread.currentThread().getName() + " calls doSave, content = " + content);
        Writer writer = new FileWriter(fileName);
        writer.write(content);
        writer.close();
    }
}
