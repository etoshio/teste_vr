package br.com.teste.vr.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisorException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CardException.class)
    protected ResponseEntity<Object> handleCard(CardException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getRequestDto());
        body.put("error_description", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.valueOf(ex.getErrorCode()));
    }

    @ExceptionHandler(TransactionException.class)
    protected ResponseEntity<Object> handleTransaction(TransactionException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error_description", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.valueOf(ex.getErrorCode()));
    }

}