package by.it_academy.jd2.MJD29522.fitness.web;

import by.it_academy.jd2.MJD29522.fitness.core.dto.exception.MultipleErrorResponseDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.exception.SingleErrorResponseDTO;
import by.it_academy.jd2.MJD29522.fitness.core.exception.BlankFieldException;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.Error;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.MultipleErrorResponse;
import by.it_academy.jd2.MJD29522.fitness.core.exception.error.SingleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleAllExceptions(Exception e){
        //Error anyError = new Error("prosto exception", e)
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Exception("Просто ошибка"));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public SingleErrorResponseDTO findSingleErrors(SingleErrorResponse e){
        return new SingleErrorResponseDTO("error", e.getMessage());
    }

//

    @ExceptionHandler  (value = {BlankFieldException.class})
   // @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<SingleErrorResponseDTO>> handleBlankField(BlankFieldException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(List.of(new SingleErrorResponseDTO(e.getMessage())));

               // new MultipleErrorResponseDTO();
               // .getLogref(), e.getListErrors());
    }
}
