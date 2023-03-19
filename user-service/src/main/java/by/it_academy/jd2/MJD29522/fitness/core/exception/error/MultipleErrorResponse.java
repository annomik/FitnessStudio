package by.it_academy.jd2.MJD29522.fitness.core.exception.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MultipleErrorResponse extends RuntimeException{

    private String logref;
    private List<Error> errors = new ArrayList<>();

    public MultipleErrorResponse() {
    }

    public MultipleErrorResponse(String logref, List<Error> errors) {
        this.logref = "structured_error";
        this.errors = errors;
    }

    public String getLogref() {
        return logref;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public void setErrors(Error error) {
        this.errors.add(error);
    }

    @Override
    public String toString() {
        return "MultipleErrorResponse{" +
                "logref='" + logref + '\'' +
                ", errors=" + errors +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultipleErrorResponse that = (MultipleErrorResponse) o;
        return Objects.equals(logref, that.logref) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logref, errors);
    }
}
