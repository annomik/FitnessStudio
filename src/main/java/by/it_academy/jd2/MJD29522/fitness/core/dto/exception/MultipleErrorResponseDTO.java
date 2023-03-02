package by.it_academy.jd2.MJD29522.fitness.core.dto.exception;

import by.it_academy.jd2.MJD29522.fitness.core.exception.error.Error;

import java.util.List;

public class MultipleErrorResponseDTO {

    private String logref;
    private List<Error> error;

    public MultipleErrorResponseDTO() {
    }

    public MultipleErrorResponseDTO(String logref, List<Error> error) {
        this.logref = logref;
        this.error = error;
    }

    public MultipleErrorResponseDTO(List<Error> error) {
        this.logref = "error";
        this.error = error;
    }

    public String getLogref() {
        return logref;
    }

    public void setLogref(String logref) {
        this.logref = logref;
    }

    public List<Error> getError() {
        return error;
    }

    public void setError(List<Error> error) {
        this.error = error;
    }
}
