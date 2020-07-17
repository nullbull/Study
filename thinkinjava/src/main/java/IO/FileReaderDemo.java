package main.java.IO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Auth justinniu
 * @Date 2018/8/27
 * @Desc
 */
public class FileReaderDemo {
    public static void main(String[] args) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("d:\\testIO\\test.txt");
//            char s[] = new char[32];
//            int hasRead = 0;
            StringBuilder sb = new StringBuilder();
//            while ((hasRead = fileReader.read(s)) > 0) {
//                sb.append(s);
//            }
//            System.out.println(s);
            char[] buf = new char[32];
            int hasRead = 0;
            while ((hasRead = fileReader.read(buf)) > 0) {
                sb.append(buf, 0, hasRead);
                System.out.println(new String(buf, 0, hasRead));
            }
            System.out.println(sb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
