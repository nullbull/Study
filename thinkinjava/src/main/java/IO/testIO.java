package main.java.IO;
 import java.io.*;

public class testIO {
    public static void FileInputStreamTest() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("tmp2.txt");
        byte[] buf = new byte[1024];
        int hasRead = 0;
        while ((hasRead = fileInputStream.read(buf)) > 0) {
            System.out.println(hasRead);

            System.out.println(new String(buf, 0, hasRead));
            System.out.println(hasRead);

        }
        fileInputStream.close();
    }

    public static void FileReaderTest() throws IOException {
        try {
            FileReader fileReader = new FileReader("tmp2.txt");
            char[] buf = new char[32];
            int hasRead = 0;
            while ((hasRead = fileReader.read(buf)) > 0) {
                System.out.println(new String(buf, 0, hasRead));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void FileOutputStreamTest() throws FileNotFoundException,IOException{
        try (
            FileInputStream fileInputStream = new FileInputStream("tmp2.txt");
            FileOutputStream fileOutputStream = new FileOutputStream("tmp3.txt");
        ){
            byte []buf = new byte[4];
            int hasRead = 0;
            while ((hasRead = fileInputStream.read(buf) ) > 0){
                System.out.println(hasRead);
                fileOutputStream.write(buf, 0, hasRead);
            }
            System.out.println("Write success");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void FileWriterTest() throws IOException{
        try(FileWriter fileWriter = new FileWriter("tmp4.txt")){
            fileWriter.write("zzzzz\r\n");
            fileWriter.write("nnnnn\r\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String []args) throws IOException{
        //FileWriterTest();
        //FileReaderTest();
        //FileOutputStreamTest();
        FileInputStreamTest();
    }

}
