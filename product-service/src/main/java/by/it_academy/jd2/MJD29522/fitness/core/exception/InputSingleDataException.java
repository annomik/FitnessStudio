package by.it_academy.jd2.MJD29522.fitness.core.exception;

import by.it_academy.jd2.MJD29522.fitness.core.dto.errors.ErrorCode;
import lombok.Getter;

@Getter
public class InputSingleDataException extends IllegalArgumentException{
    private ErrorCode errorCode;

    public InputSingleDataException(String s, ErrorCode errorCode) {
        super(s);
        this.errorCode = errorCode;
    }
}
