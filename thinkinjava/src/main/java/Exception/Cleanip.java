package main.java.Exception;

public class Cleanip {
    public static void main(String[] args){
        try{
            InputFile inputFile = new InputFile("Cleanip.java");
            try {
                String s;
                int i = 1;
                while((s = inputFile.getLine() ) != null)
                    System.out.println(s);
            }catch (Exception e){
                System.out.println("Caught in main");
                e.printStackTrace(System.out);
            }
            finally {
                inputFile.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
