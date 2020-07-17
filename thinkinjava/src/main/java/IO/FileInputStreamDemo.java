package main.java.IO;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Auth justinniu
 * @Date 2018/8/27
 * @Desc
 */
public class FileInputStreamDemo {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("d:\\testIO\\test.txt");
            StringBuilder sb = new StringBuilder();
            byte buf[] = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(buf)) != -1) {

                sb.append(new String(buf, 0, len));
            }
            System.out.println(sb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
