package br.com.teste.vr.data.exception;

import lombok.Getter;

public class ErrorException extends RuntimeException {

    @Getter
    private ErrorType model;

    public ErrorException(ErrorType model, Throwable cause) {
        super(model.getMessage(), cause);
        this.model = model;
    }

    public ErrorException(ErrorType model) {
        this(model, null);
    }

}
