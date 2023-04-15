package by.it_academy.jd2.MJD29522.fitness.core.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SingleErrorResponse extends RuntimeException{

    private List<ErrorForSingleResponse> errors = new ArrayList<>();

    public SingleErrorResponse() {
    }

    public SingleErrorResponse(List<ErrorForSingleResponse> errors) {
        this.errors = errors;
    }

    public SingleErrorResponse(String s) {
        this.errors.add(new ErrorForSingleResponse(s));
    }

    public List<ErrorForSingleResponse> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorForSingleResponse> errors) {
        this.errors = errors;
    }

    public void setErrors(ErrorForSingleResponse errorForSingleResponse) {
        this.errors.add(errorForSingleResponse);
    }

    @Override
    public String toString() {
        return "SingleErrorResponse{" +
                "errors=" + errors +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleErrorResponse that = (SingleErrorResponse) o;
        return Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors);
    }
}
