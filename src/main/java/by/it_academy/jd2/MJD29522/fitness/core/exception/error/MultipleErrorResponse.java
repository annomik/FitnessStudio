package by.it_academy.jd2.MJD29522.fitness.core.exception.error;

import java.util.ArrayList;
import java.util.List;

public class MultipleErrorResponse extends RuntimeException{

    private String logref;
    private List<Error> listErrors = new ArrayList<>();

    public MultipleErrorResponse() {
    }

    public MultipleErrorResponse(String logref, List<Error> listErrors) {
        this.logref = "structured_error";
        this.listErrors = listErrors;
    }

    public String getLogref() {
        return logref;
    }

    public void setLogref(String logref) {
        this.logref = logref;
    }

    public List<Error> getListErrors() {
        return listErrors;
    }

    public void setListErrors(List<Error> listErrors) {
        this.listErrors = listErrors;
    }

    public void setErrors(Error error) {
        this.listErrors.add(error);
    }
}
