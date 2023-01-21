package br.com.teste.vr.data.exception;

import lombok.Getter;

public class TransactionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @Getter
    private Integer errorCode;

    public TransactionException(final String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public TransactionException(final Throwable cause) {
        super(cause);
    }
}
