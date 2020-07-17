package test.IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Auth justinniu
 * @Date 2018/8/27
 * @Desc
 */
public class FileOutputStreamDemo {
    public static void main(String[] args) {
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream("D:\\testIO\\test.txt");
            String s = "啊哈哈哈哈哈哈哈哈哈哈哈哈";
            stream.write(s.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
