package by.it_academy.jd2.MJD29522.fitness.core.exception;

import lombok.Getter;
import by.it_academy.jd2.MJD29522.fitness.core.dto.errors.ErrorCode;

@Getter
public class ConversionTypeException extends RuntimeException {
    private ErrorCode errorCode;

    public ConversionTypeException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}

