package by.it_academy.jd2.MJD29522.fitness.core.exception;

import by.it_academy.jd2.MJD29522.fitness.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ConversionTypeException extends RuntimeException {
    private ErrorCode errorCode;

    public ConversionTypeException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}

