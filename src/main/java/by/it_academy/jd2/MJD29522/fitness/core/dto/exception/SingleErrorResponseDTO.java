package by.it_academy.jd2.MJD29522.fitness.core.dto.exception;

public class SingleErrorResponseDTO {

    private String logref;
    private String message;

    public SingleErrorResponseDTO() {
    }

    public SingleErrorResponseDTO(String logref, String message) {
        this.logref = "error";
        this.message = message;
    }

    public SingleErrorResponseDTO(String message) {
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
