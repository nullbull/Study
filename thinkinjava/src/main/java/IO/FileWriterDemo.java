package main.java.IO;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Auth justinniu
 * @Date 2018/8/27
 * @Desc
 */
public class FileWriterDemo {
    public static void main(String[] args)  {
        FileWriter fileWriter = null;
        try {
           fileWriter = new FileWriter("d:\\testIO\\test.txt");
           fileWriter.write("niu");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fileWriter != null) fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
