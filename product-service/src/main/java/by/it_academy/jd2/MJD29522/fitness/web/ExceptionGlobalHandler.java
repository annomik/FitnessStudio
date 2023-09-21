package by.it_academy.jd2.MJD29522.fitness.web;

import by.it_academy.jd2.MJD29522.fitness.core.dto.errors.*;
import by.it_academy.jd2.MJD29522.fitness.core.exception.ConversionTypeException;
import by.it_academy.jd2.MJD29522.fitness.core.exception.InputSingleDataException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionGlobalHandler {
    @ExceptionHandler
    public ResponseEntity<List<SingleErrorResponse>> handleInputSingleDataException(InputSingleDataException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(List.of(new SingleErrorResponse(e.getErrorCode(), e.getMessage())));
    }

    @ExceptionHandler(value = {ConversionTypeException.class, Exception.class})
    public ResponseEntity<List<SingleErrorResponse>> handleConversionTypeException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(new SingleErrorResponse(ErrorCode.ERROR, e.getMessage())));
    }

    @ExceptionHandler
    public ResponseEntity<MultipleErrorResponse> handleMultiErrors(MethodArgumentNotValidException e) {
        List<LocalError> errorList = e.getBindingResult().getAllErrors()
                .stream().map(error -> new LocalError(((FieldError) error).getField(), error.getDefaultMessage())).toList();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MultipleErrorResponse(ErrorCode.STRUCTURED_ERROR, errorList));
    }

    @ExceptionHandler
    public ResponseEntity<List<SingleErrorResponse>> handle(IllegalArgumentException e){
        List<SingleErrorResponse> errors = new ArrayList<>();
        errors.add(new SingleErrorResponse(ErrorCode.ERROR,  e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler
    public ResponseEntity<MultipleErrorResponse> handle(ConstraintViolationException e) {
        List<LocalError> localErrors = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            String name = null;
            for (Path.Node node : constraintViolation.getPropertyPath()) {
                name = node.getName();
            }
            localErrors.add(new LocalError(name, constraintViolation.getMessage()));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MultipleErrorResponse(ErrorCode.STRUCTURED_ERROR, localErrors));
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<List<SingleErrorResponse>> handleNPE(IllegalArgumentException e){
        List<SingleErrorResponse> errors = new ArrayList<>();
        errors.add(new SingleErrorResponse(ErrorCode.ERROR,  e.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }



}
