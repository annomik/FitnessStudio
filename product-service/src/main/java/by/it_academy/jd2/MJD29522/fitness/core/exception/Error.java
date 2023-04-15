package by.it_academy.jd2.MJD29522.fitness.core.exception;

import java.util.Objects;

public class Error {
    private String field;
    private String message;

    public Error() {
    }

    public Error(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error error = (Error) o;
        return Objects.equals(field, error.field) && Objects.equals(message, error.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, message);
    }

    @Override
    public String toString() {
        return "Error{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
