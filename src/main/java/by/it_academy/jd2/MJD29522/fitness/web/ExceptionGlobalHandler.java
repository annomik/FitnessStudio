package by.it_academy.jd2.MJD29522.fitness.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<?> handleNPE(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(new Exception("Ошибка"));
  }


}
