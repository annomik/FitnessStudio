package by.it_academy.jd2.MJD29522.fitness.core.exception.error;

import java.util.Objects;

public class ErrorForSingleResponse {

    private String logref;
    private String message;

    public ErrorForSingleResponse() {
    }

    public ErrorForSingleResponse(String logref, String message) {
        this.logref = logref;
        this.message = message;
    }

    public ErrorForSingleResponse(String message) {
        this.logref = "error";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorForSingleResponse that = (ErrorForSingleResponse) o;
        return Objects.equals(logref, that.logref) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logref, message);
    }

    @Override
    public String toString() {
        return "ErrorForSingleResponse{" +
                "logref='" + logref + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
