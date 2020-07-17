package main.java.Exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputFile {
    private BufferedReader in;
    public InputFile(String name) throws Exception{
        try {
            in = new BufferedReader(new FileReader(name));
        }catch (FileNotFoundException e){
            throw e;
        }catch (Exception e){
            try{
                in.close();
            }catch (IOException e1){
                System.out.println("in.close() unsuccessful");
            }
            throw e;
        }finally {

        }
    }
    public String getLine(){
        String s = "";
        try{
            s = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
    public void dispose(){
        try{
            in.close();
            System.out.println("colsed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
