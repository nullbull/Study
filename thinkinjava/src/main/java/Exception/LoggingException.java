package main.java.Exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

class LoggingException extends Throwable {
    private static Logger logger =
            Logger.getLogger("LoggingException");
    public LoggingException(){
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
    public static class LoggingExceptions{
        public static void main(String[] args){
            try{
                throw new LoggingException();
            } catch (LoggingException e) {
                System.out.println("Catch" + e);
            }

            try{
                throw new LoggingException();
            } catch (LoggingException e) {
                System.err.println("Catch" + e);
            }
        }
    }
}
