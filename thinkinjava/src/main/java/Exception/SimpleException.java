package Java.Exception;

class SimpleException extends Exception {

}
class InheritingExceptions{
    public void f() throws SimpleException{
        System.out.println("Throw SimleException from f()");
        throw new SimpleException();
    }
    public static void main(String[] args){
        InheritingExceptions sed = new InheritingExceptions();
        try{
            sed.f();
        } catch (SimpleException e) {
            System.out.println("Catch it");
            //e.printStackTrace();
        }
    }
}