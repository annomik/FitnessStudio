package by.it_academy.jd2.MJD29522.fitness.core.exception.error;

public class SingleErrorResponse extends RuntimeException{

    private String logref;
    private String message;

    public SingleErrorResponse() {
    }

    public SingleErrorResponse(String logref, String message) {
        this.logref = logref;
        this.message = message;
    }

    public SingleErrorResponse(String message) {
        this.message = message;
    }

    public String getLogref() {
        return logref;
    }

    public void setLogref(String logref) {
        this.logref = logref;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
