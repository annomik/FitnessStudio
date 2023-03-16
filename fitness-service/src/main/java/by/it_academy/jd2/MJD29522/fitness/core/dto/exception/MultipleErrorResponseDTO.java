package by.it_academy.jd2.MJD29522.fitness.core.dto.exception;

import by.it_academy.jd2.MJD29522.fitness.core.exception.error.Error;

import java.util.List;

public class MultipleErrorResponseDTO {

    private String logref;
    private List<Error> errors;

    public MultipleErrorResponseDTO() {
    }

    public MultipleErrorResponseDTO(String logref, List<Error> errors) {
        this.logref = "structured_error";
        this.errors = errors;
    }

    public MultipleErrorResponseDTO(List<Error> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public String getLogref() {
        return logref;
    }

    public void setLogref(String logref) {
        this.logref = logref;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
