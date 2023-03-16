package by.it_academy.jd2.MJD29522.fitness.core.exception;

public class BlankFieldException extends RuntimeException{

    //private String message;


    public BlankFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlankFieldException(String message) {
        super(message);
    }
}
