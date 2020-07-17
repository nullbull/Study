package main.java.IO;
import java.io.*;
public class IOTest {
    public static void printStream() throws FileNotFoundException, IOException{
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("tmp2.txt");
                PrintStream printStream = new PrintStream(fileOutputStream);
        ){
            printStream.println("TeXtText\n");
            printStream.println(new IOTest());
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Finnsh");
    }
    public static void stringNode() throws IOException{
        String string = "1";
        char[] buf = new char[32];
        int hasRead = 0;
        try (StringReader stringReader = new StringReader("112")){
            while ((hasRead = stringReader.read(buf)) > 0){
                System.out.print(new String(buf, 0, hasRead));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try (StringWriter stringWriter = new StringWriter()){
            stringWriter.write("我想我妈了\n");
            stringWriter.write("今天给她打个电话\n");
            System.out.println(stringWriter);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void keyIn() throws IOException{
        try (
                InputStreamReader reader = new InputStreamReader(System.in);
                BufferedReader bufferedReader = new BufferedReader(reader);
                ){
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                if(line.equals("exit"))
                    break;
                System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception{
       //printStream();
        // stringNode();
        keyIn();
        //InputStream 一次读取一个字节
        //InputStreamReader 一次读取一个字符
        //BufferedReader 一次读取一行
    }
}
