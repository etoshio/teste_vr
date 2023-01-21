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

    @ExceptionHandler(ErrorException.class)
    protected ResponseEntity<Object> handleApiKeyException(ErrorException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("source", "INTERNO" );
        body.put("error", ex.getModel().getMessage());
        body.put("error_description", ex.getModel().getMessage());
        return new ResponseEntity<>(body, ex.getModel().getStatus());
    }


}